package com.example.core.kafka;

import com.example.core.dto.Event;
import com.example.core.services.OrquestratorService;
import com.example.core.utils.JsonUtil;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@KafkaListener(groupId = "${kafka.consumer.group-id}")
public class Consumer {

    private static final Logger LOG = LoggerFactory.getLogger(Consumer.class);
    @Inject
    private OrquestratorService orquestratorService;
    @Inject
    private JsonUtil jsonUtil;

    @Topic("${kafka.topic.start}")
    public void consumerStartEvent(String payload){
        LOG.info("Receiving event {} from start topic" , payload);
        Event event = jsonUtil.toEvent(payload);
        orquestratorService.start(event);
    }
    @Topic("${kafka.topic.orquestrator}")
    public void consumerOrquestratorEvent(String payload){
        LOG.info("Receiving event {} from orquestrator topic" , payload);
        Event event = jsonUtil.toEvent(payload);
        orquestratorService.continueSaga(event);
    }
    @Topic("${kafka.topic.finish-success}")
    public void consumerFinishSuccessEvent(String payload){
        LOG.info("Receiving event {} from finish-success topic" , payload);
        Event event = jsonUtil.toEvent(payload);
        orquestratorService.finishSuccess(event);
    }
    @Topic("${kafka.topic.finish-fail}")
    public void consumerFinishFailEvent(String payload){
        LOG.info("Receiving ending notification event {} from finish-fail topic" , payload);
        Event event = jsonUtil.toEvent(payload);
        orquestratorService.finishFail(event);
    }
}
