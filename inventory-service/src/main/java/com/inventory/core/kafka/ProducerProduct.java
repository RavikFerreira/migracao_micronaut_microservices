package com.inventory.core.kafka;

import io.micronaut.context.annotation.Value;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@RequiredArgsConstructor
public class ProducerProduct {

    private static final Logger LOG = LoggerFactory.getLogger(ProducerProduct.class);

    @Inject
    private KafkaProducer<String, Object> kafkaProducer;

    @Value("${kafka.topic.orchestrator-product}")
    private String orchestratorTopic;

    public void sendEvent(String payload){
        try {
            LOG.info("Sending event to topic {} with data {} ", orchestratorTopic, payload);
            ProducerRecord<String, Object> record = new ProducerRecord<>(orchestratorTopic, payload);
            kafkaProducer.send(record);
        } catch (Exception e) {
            LOG.error("Error trying to send data to topic {} with data {}", orchestratorTopic, payload, e);
        }
    }
}
