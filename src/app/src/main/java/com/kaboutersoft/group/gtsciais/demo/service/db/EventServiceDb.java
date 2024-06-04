package com.kaboutersoft.group.gtsciais.demo.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaboutersoft.group.gtsciais.demo.model.Event;
import com.kaboutersoft.group.gtsciais.demo.repository.EventRepository;

import java.util.List;

@Service
public class EventServiceDb {
    private final EventRepository EventRepository;

    @Autowired
    public EventServiceDb(EventRepository EventRepository) {
        this.EventRepository = EventRepository;
    }

    public List<Event> getAllEvents() {
        return EventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return EventRepository.findById(id).orElse(null);
    }

    public Event createEvent(Event Event) {
        return EventRepository.save(Event);
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        if (!EventRepository.existsById(id)) {
            return null;
        }
        updatedEvent.setId(id);
        return EventRepository.save(updatedEvent);
    }

    public boolean deleteEvent(Long id) {
        if (!EventRepository.existsById(id)) {
            return false;
        }
        EventRepository.deleteById(id);
        return true;
    }
}
