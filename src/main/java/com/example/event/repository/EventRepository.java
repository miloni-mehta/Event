package com.example.event.repository;

import com.example.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<Event> findByNameContainingIgnoreCase(String name);
    List<Event> findByLocation(String location);
    List<Event> findByOrganizerId(Long organizerId);
    List<Event> findByCategory(String category);
}
