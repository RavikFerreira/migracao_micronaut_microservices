package com.tables.core.service;

import com.tables.core.dto.EventFilters;
import com.tables.core.models.Event;
import com.tables.core.repository.EventRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

import static io.micronaut.core.util.StringUtils.isEmpty;

@Singleton
public class EventService {
    private static final Logger LOG = LoggerFactory.getLogger(EventService.class);

    @Inject
    private EventRepository eventRepository;

    public void notify(Event event){
        event.setTableId(event.getTableId());
        event.setCreatedAt(LocalDateTime.now());
        save(event);
        LOG.info("TableID {} with notified! TransactionID: {} " + event.getTableId(),  event.getTransactionId());
    }
    public List<Event> findAll(){
        return eventRepository.findAllOrderByCreatedAtDesc();
    }

    private Event findByTableId(String tableId){
        return eventRepository.findTop1ByTableIdOrderByCreatedAtDesc(tableId)
                .orElseThrow(() -> new RuntimeException("Event not found by tableID."));
    }

    private Event findByTransactionId(String transactionId){
        return eventRepository.findTop1ByTransactionIdOrderByCreatedAtDesc(transactionId)
                .orElseThrow(() -> new RuntimeException("Event not found by transactionID."));
    }

    public Event findByFilters(EventFilters filters){
        validateEmptyFilters(filters);
        if(!isEmpty(filters.getTableId())){
            return findByTableId(filters.getTableId());
        }
        else {
            return findByTransactionId(filters.getTransactionId());
        }
    }

    private void validateEmptyFilters(EventFilters filters){
        if(isEmpty(filters.getTableId())&& isEmpty(filters.getTransactionId())){
            throw new RuntimeException("TableId or TransactionID must be informed.");
        }
    }

    public Event save(Event event){
        return eventRepository.save(event);
    }
}

