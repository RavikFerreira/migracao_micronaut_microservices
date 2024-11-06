package com.tables.core.controller;

import com.tables.core.dto.EventFilters;
import com.tables.core.models.Event;
import com.tables.core.service.EventService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

import java.util.List;

@Controller("event/")
public class EventController {

    @Inject
    private EventService eventService;

    @Post("filters")
    public Event findByFilters(@Body EventFilters filters){
        return eventService.findByFilters(filters);
    }

    @Get("list")
    public List<Event> findAll(){
        return eventService.findAll();
    }
}
