//package com.example.core.kafka;
//
//import io.micronaut.context.annotation.Value;
//import jakarta.inject.Inject;
//import jakarta.inject.Singleton;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.ProducerRecord;
//
//@Slf4j
//@Singleton
//@RequiredArgsConstructor
//public class Producer {
//
//    @Inject
//    private KafkaProducer<String, String> kafkaProducer;
//
//    @Value("${kafka.topic.orquestrator}")
//    private String orquestratorTopic;
//
//    public void sendEvent(String payload, String topic){
//        try {
//            log.info("Sending event to topic {} with data {}", orquestratorTopic, payload);
//            ProducerRecord<String, String> record = new ProducerRecord<>(orquestratorTopic, payload);
//            kafkaProducer.send(record, (metadata, exception) -> {
//                if (exception == null) {
//                    log.info("Successfully sent record to topic {} with metadata {}", orquestratorTopic, metadata);
//                } else {
//                    log.error("Error sending record to topic {} with data {}", orquestratorTopic, payload, exception);
//                }
//            });
//        } catch (Exception e) {
//            log.error("Error trying to send data to topic {} with data {}", orquestratorTopic, payload, e);
//        }
//    }
//}
