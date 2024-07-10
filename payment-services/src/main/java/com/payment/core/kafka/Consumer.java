package com.payment.core.kafka;

import com.payment.core.utils.JsonUtil;
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

    @Topic("${kafka.topic.payment-success}")
    public void consumerPaymentSuccessEvent(String payload){
        LOG.info("Receiving ending notification event {} from payment-success topic" , payload);
        var event = jsonUtil.toEvent(payload);
        LOG.info(event.toString());
    }
    @Topic("${kafka.topic.payment-fail}")
    public void consumerPaymentFailEvent(String payload){
        LOG.info("Receiving ending notification event {} from payment-fail topic" , payload);
        var event = jsonUtil.toEvent(payload);
        LOG.info(event.toString());
    }
}
