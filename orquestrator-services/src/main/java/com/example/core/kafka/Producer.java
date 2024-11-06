package com.example.core.kafka;

import io.micronaut.context.annotation.Value;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class Producer {

    private static final Logger LOG = LoggerFactory.getLogger(Producer.class);

    @Inject
    private KafkaProducer<String, String> kafkaProducer;

    @Value("${kafka.topic.orquestrator}")
    private String orquestratorTopic;

    public void sendEvent(String payload, String topic){
        try {
            LOG.info("Sending event to topic {} with data {}", orquestratorTopic, topic, payload);
            ProducerRecord<String, String> record = new ProducerRecord<>(orquestratorTopic, topic, payload);
            kafkaProducer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    LOG.info("Successfully sent record to topic {} with metadata {}", orquestratorTopic, metadata);
                } else {
                    LOG.error("Error sending record to topic {} with data {}", orquestratorTopic, payload, exception);
                }
            });
        } catch (Exception e) {
            LOG.error("Error trying to send data to topic {} with data {}", orquestratorTopic, topic, payload, e);
        }
    }
}
