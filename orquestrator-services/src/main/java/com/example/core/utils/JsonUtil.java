//package com.example.core.utils;
//
//import io.micronaut.serde.ObjectMapper;
//import jakarta.inject.Singleton;
//import lombok.AllArgsConstructor;
//
//@Singleton
//@AllArgsConstructor
//public class JsonUtil {
//
//    private final ObjectMapper objectMapper;
//
//    public String toJson(Object object){
//        try{
//            return objectMapper.writeValueAsString(object);
//        } catch (Exception e){
//            return "";
//        }
//    }
//
////    public Event toEvent(String json){
////        try{
////            return objectMapper.readValue(json, Event.class);
////        } catch (Exception e){
////            return null;
////        }
////    }
//}
