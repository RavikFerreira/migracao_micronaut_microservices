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
public class ConsumerProduct {

    private static final Logger LOG = LoggerFactory.getLogger(ConsumerProduct.class);

    @Inject
    private InventoryService inventoryService;
    @Inject
    private JsonUtil jsonUtil;

    @Topic("${kafka.topic.inventory-product-success}")
    public void consumerInventorySuccessEvent(String payload){
        LOG.info("Receiving success event {} from inventory-success topic" , payload);
        EventProduct event = jsonUtil.toProductEvent(payload);
        inventoryService.createInventory(event);
    }
}
