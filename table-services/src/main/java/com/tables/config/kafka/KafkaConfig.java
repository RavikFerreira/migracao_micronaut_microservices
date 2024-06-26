package com.tables.config.kafka;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;

import jakarta.inject.Singleton;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;

import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

@Factory
@RequiredArgsConstructor
public class KafkaConfig {

    private static final int PARTITIONS_COUNT = 1;
    private static final int REPLICA_COUNT = 1;

    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;
    @Value("${kafka.consumer.group-id}")
    private String groupId;
    @Value("${kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Value("${kafka.topic.start}")
    private String startTopic;
    @Value("${kafka.topic.notify}")
    private String notifyTopic;


    @Singleton
    private KafkaConsumer<String, String> consumeProps() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        return new KafkaConsumer<>(props);
    }


    private KafkaProducer<String, String> producerProps() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new KafkaProducer<>(props);
    }

    private NewTopic buildTopic(String name){
        return new NewTopic(name, PARTITIONS_COUNT, (short) REPLICA_COUNT);
    }

    @Bean
    public NewTopic startTopic(){
        return buildTopic(startTopic);
    }

    @Bean
    public NewTopic notifyTopic(){
        return buildTopic(notifyTopic);
    }
}
