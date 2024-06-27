//package com.example.core.kafka;
//
//import com.example.core.utils.JsonUtil;
//import io.micronaut.configuration.kafka.annotation.KafkaListener;
//import io.micronaut.configuration.kafka.annotation.Topic;
//import jakarta.inject.Inject;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@AllArgsConstructor
//@KafkaListener(groupId = "${kafka.consumer.group-id}")
//public class Consumer {
//
//    @Inject
//    private JsonUtil jsonUtil;
//
//    @Topic("${kafka.topic.notify}")
//    public void consumerNotifyEvent(String payload){
//        log.info("Receiving ending notification event {} from notify topic" , payload);
//        var event = jsonUtil.toEvent(payload);
//        log.info(event.toString());
//    }
//}
