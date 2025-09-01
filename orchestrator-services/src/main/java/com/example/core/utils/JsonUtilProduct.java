package com.example.core.utils;

import com.example.core.dto.Event;
import com.example.core.dto.EventProduct;
import io.micronaut.serde.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;

@Singleton
@AllArgsConstructor
public class JsonUtilProduct {

    @Inject
    private ObjectMapper objectMapper;

    public String toJson(Object object){
        try{
            return objectMapper.writeValueAsString(object);
        } catch (Exception e){
            return "";
        }
    }

    public EventProduct toEvent(String json){
        try{
            return objectMapper.readValue(json, EventProduct.class);
        } catch (Exception e){
            return null;
        }
    }
}
