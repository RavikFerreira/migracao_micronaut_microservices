package com.example.config.kafka;

import com.example.enums.ETopic;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

@Factory
public class KafkaConfig {

    private static final int PARTITIONS_COUNT = 1;
    private static final int REPLICA_COUNT = 1;

    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;
    @Value("${kafka.consumer.group-id}")
    private String groupId;
    @Value("${kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Singleton
    @Bean
    public KafkaConsumer<String, String> consumeProps() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        return new KafkaConsumer<>(props);
    }

    @Singleton
    @Bean
    public KafkaProducer<String, String> producerProps() {
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
        return buildTopic(ETopic.START.getTopic());
    }

    @Bean
    public NewTopic orquestratorTopic(){
        return buildTopic(ETopic.ORQUESTRATOR.getTopic());
    }
    @Bean
    public NewTopic notifyTopic(){
        return buildTopic(ETopic.NOTIFY.getTopic());
    }
    @Bean
    public NewTopic menu_successTopic(){
        return buildTopic(ETopic.MENU_SUCCESS.getTopic());
    }

    @Bean
    public NewTopic menu_failTopic(){
        return buildTopic(ETopic.MENU_FAIL.getTopic());
    }

    @Bean
    public NewTopic orders_successTopic(){
        return buildTopic(ETopic.ORDERS_SUCCESS.getTopic());
    }

    @Bean
    public NewTopic orders_failTopic(){
        return buildTopic(ETopic.ORDERS_FAIL.getTopic());
    }
}
