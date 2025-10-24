package com.example.core.kafka;

import com.example.core.dto.EventProduct;
import com.example.core.services.OrchestratorProductService;
import com.example.core.utils.JsonUtilProduct;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@KafkaListener(groupId = "${kafka.consumer.group-id}")
public class ConsumerProduct {

    private static final Logger LOG = LoggerFactory.getLogger(ConsumerProduct.class);
    @Inject
    private OrchestratorProductService orchestratorProductService;
    @Inject
    private JsonUtilProduct jsonUtilProduct;

    @Topic("${kafka.topic.start}")
    public void consumerStartEvent(String payload){
        LOG.info("Receiving event {} from start topic" , payload);
        EventProduct event = jsonUtilProduct.toEvent(payload);
        orchestratorProductService.start(event);
    }
    @Topic("${kafka.topic.orchestrator}")
    public void consumerOrchestratorEvent(String payload){
        LOG.info("Receiving event {} from orchestrator topic" , payload);
        EventProduct event = jsonUtilProduct.toEvent(payload);
        orchestratorProductService.continueSaga(event);
    }
    @Topic("${kafka.topic.finish-success}")
    public void consumerFinishSuccessEvent(String payload){
        LOG.info("Receiving event {} from finish-success topic" , payload);
        EventProduct event = jsonUtilProduct.toEvent(payload);
        orchestratorProductService.finishSuccess(event);
    }
    @Topic("${kafka.topic.finish-fail}")
    public void consumerFinishFailEvent(String payload){
        LOG.info("Receiving ending notification event {} from finish-fail topic" , payload);
        EventProduct event = jsonUtilProduct.toEvent(payload);
        orchestratorProductService.finishFail(event);
    }
}
