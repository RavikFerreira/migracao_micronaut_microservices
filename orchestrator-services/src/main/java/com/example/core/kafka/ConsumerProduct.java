package com.example.core.kafka;

import com.example.core.dto.Event;
import com.example.core.dto.EventProduct;
import com.example.core.services.OrchestratorProductService;
import com.example.core.services.OrchestratorService;
import com.example.core.utils.JsonUtil;
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
    private JsonUtil jsonUtil;

    @Topic("${kafka.topic.start-product}")
    public void consumerStartProductEvent(String payload){
        LOG.info("Receiving event {} from start topic" , payload);
        EventProduct event = jsonUtil.toProductEvent(payload);
        orchestratorProductService.start(event);
    }

    @Topic("${kafka.topic.orchestrator-product}")
    public void consumerOrchestratorProductEvent(String payload){
        LOG.info("Receiving event {} from orchestrator topic" , payload);
        EventProduct event = jsonUtil.toProductEvent(payload);
        orchestratorProductService.continueSaga(event);
    }

    @Topic("${kafka.topic.finish-success}")
    public void consumerFinishSuccessProductEvent(String payload){
        LOG.info("Receiving event {} from finish-success topic" , payload);
        EventProduct event = jsonUtil.toProductEvent(payload);
        orchestratorProductService.finishSuccess(event);
    }
    @Topic("${kafka.topic.finish-fail}")
    public void consumerFinishFailProductEvent(String payload){
        LOG.info("Receiving ending notification event {} from finish-fail topic" , payload);
        EventProduct event = jsonUtil.toProductEvent(payload);
        orchestratorProductService.finishFail(event);
    }
}
