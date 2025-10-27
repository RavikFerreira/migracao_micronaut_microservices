package com.inventory.core.kafka;

import com.inventory.core.dto.EventProduct;
import com.inventory.core.service.InventoryProductService;
import com.inventory.core.utils.JsonUtilProduct;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@KafkaListener(groupId = "${kafka.consumer.group-id}")
@RequiredArgsConstructor
public class ConsumerProduct {

    private static final Logger LOG = LoggerFactory.getLogger(ConsumerProduct.class);

    @Inject
    private InventoryProductService inventoryProductService;
    @Inject
    private JsonUtilProduct jsonUtilProduct;

    @Topic("${kafka.topic.inventory-success}")
    public void consumerInventorySuccessEvent(String payload){
        LOG.info("Receiving success event {} from inventory-success topic" , payload);
        EventProduct event = jsonUtilProduct.toEvent(payload);
        inventoryProductService.updateInventory(event);
    }
    @Topic("${kafka.topic.inventory-fail}")
    public void consumerInventoryFailEvent(String payload){
        LOG.info("Receiving rollback event {} from inventory-fail topic" , payload);
        EventProduct event = jsonUtilProduct.toEvent(payload);
        inventoryProductService.rollbackInventory(event);
    }
}
