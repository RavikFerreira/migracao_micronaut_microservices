package com.example.core.kafka;

import com.example.core.utils.JsonUtil;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
@KafkaListener(groupId = "${kafka.consumer.group-id}")
public class Consumer {

    private static final Logger LOG = LoggerFactory.getLogger(Consumer.class);

    @Inject
    private JsonUtil jsonUtil;

    @Topic("${kafka.topic.start}")
    public void consumerStartEvent(String payload){
        LOG.info("Receiving ending notification event {} from start topic" , payload);
        var event = jsonUtil.toEvent(payload);
        LOG.info(event.toString());
    }
    @Topic("${kafka.topic.orquestrator}")
    public void consumerOrquestratorEvent(String payload){
        LOG.info("Receiving ending notification event {} from orquestrator topic" , payload);
        var event = jsonUtil.toEvent(payload);
        LOG.info(event.toString());
    }
    @Topic("${kafka.topic.finish-success}")
    public void consumerFinishSuccessEvent(String payload){
        LOG.info("Receiving ending notification event {} from finish-success topic" , payload);
        var event = jsonUtil.toEvent(payload);
        LOG.info(event.toString());
    }
    @Topic("${kafka.topic.finish-fail}")
    public void consumerFinishFailEvent(String payload){
        LOG.info("Receiving ending notification event {} from finish-fail topic" , payload);
        var event = jsonUtil.toEvent(payload);
        LOG.info(event.toString());
    }
}
