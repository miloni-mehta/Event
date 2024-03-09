package com.example.event.service;

import com.example.event.model.Event;
import com.example.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    public Event findEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event updateEvent(Long id, Event eventDetails) {
        return eventRepository.findById(id).map(event -> {
            event.setName(eventDetails.getName());
            event.setDate(eventDetails.getDate());
            event.setLocation(eventDetails.getLocation());
            event.setOrganizerId(eventDetails.getOrganizerId());
            event.setCategory(eventDetails.getCategory());
            return eventRepository.save(event);
        }).orElse(null);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
    public List<Event> getEventsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return eventRepository.findByDateBetween(startDate, endDate);
    }

    public List<Event> searchEventsByName(String name) {
        return eventRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Event> getEventsByLocation(String location) {
        return eventRepository.findByLocation(location);
    }

    public List<Event> getEventsByOrganizerId(Long organizerId) {
        return eventRepository.findByOrganizerId(organizerId);
    }

    public List<Event> getEventsByCategory(String category) {
        return eventRepository.findByCategory(category);
    }
}
