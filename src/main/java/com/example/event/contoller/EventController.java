package com.example.event.contoller;

import com.example.event.model.Event;
import com.example.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event newEvent = eventService.saveEvent(event);
        return ResponseEntity.ok(newEvent);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.findAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.findEventById(id);
        if (event != null) {
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        Event updatedEvent = eventService.updateEvent(id, eventDetails);
        if (updatedEvent != null) {
            return ResponseEntity.ok(updatedEvent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }
    // Find events by date range
    @GetMapping("/search/byDateRange")
    public ResponseEntity<List<Event>> findByDateRange(
            @RequestParam(value = "startDate") LocalDate startDate,
            @RequestParam(value = "endDate") LocalDate endDate) {
        List<Event> events = eventService.getEventsBetweenDates(startDate, endDate);
        return ResponseEntity.ok(events);
    }

    // Find events by name
    @GetMapping("/search/byName")
    public ResponseEntity<List<Event>> findByNameContainingIgnoreCase(@RequestParam String name) {
        List<Event> events = eventService.searchEventsByName(name);
        return ResponseEntity.ok(events);
    }

    // Find events by location
    @GetMapping("/search/byLocation")
    public ResponseEntity<List<Event>> findByLocation(@RequestParam String location) {
        List<Event> events = eventService.getEventsByLocation(location);
        return ResponseEntity.ok(events);
    }

    // Find events by organizerId
    @GetMapping("/search/byOrganizerId")
    public ResponseEntity<List<Event>> findByOrganizerId(@RequestParam Long organizerId) {
        List<Event> events = eventService.getEventsByOrganizerId(organizerId);
        return ResponseEntity.ok(events);
    }

    // Find events by category
    @GetMapping("/search/byCategory")
    public ResponseEntity<List<Event>> findByCategory(@RequestParam String category) {
        List<Event> events = eventService.getEventsByCategory(category);
        return ResponseEntity.ok(events);
    }
}