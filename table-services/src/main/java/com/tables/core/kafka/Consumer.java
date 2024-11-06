package com.tables.core.kafka;

import com.tables.core.utils.JsonUtil;
import com.tables.core.service.EventService;
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
    private EventService eventService;
    @Inject
    private JsonUtil jsonUtil;

    @Topic("${kafka.topic.notify}")
    public void consumerNotifyEvent(String payload){
        LOG.info("Receiving ending notification event {} from notify topic" , payload);
        var event = jsonUtil.toEvent(payload);
        eventService.notify(event);
    }
}
