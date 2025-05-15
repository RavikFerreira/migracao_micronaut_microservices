package com.inventory.core.kafka;

import com.inventory.core.dto.Event;
import com.inventory.core.dto.EventProduct;
import com.inventory.core.service.InventoryService;
import com.inventory.core.utils.JsonUtil;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@KafkaListener(groupId = "${kafka.consumer.group-id}")
@RequiredArgsConstructor
public class Consumer {

    private static final Logger LOG = LoggerFactory.getLogger(Consumer.class);

    @Inject
    private InventoryService inventoryService;
    @Inject
    private JsonUtil jsonUtil;

    @Topic("${kafka.topic.inventory-success}")
    public void consumerInventorySuccessEvent(String payload){
        LOG.info("Receiving success event {} from inventory-success topic" , payload);
        Event event = jsonUtil.toEvent(payload);
        inventoryService.updateInventory(event);
    }
    @Topic("${kafka.topic.inventory-fail}")
    public void consumerInventoryFailEvent(String payload){
        LOG.info("Receiving rollback event {} from inventory-fail topic" , payload);
        Event event = jsonUtil.toEvent(payload);
        inventoryService.rollbackInventory(event);
    }
}
