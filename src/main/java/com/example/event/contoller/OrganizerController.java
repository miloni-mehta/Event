package com.example.event.contoller;
import com.example.event.model.Organizer;
import com.example.event.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizers")
public class OrganizerController {

    private final OrganizerService organizerService;

    @Autowired
    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @PostMapping
    public ResponseEntity<Organizer> createOrganizer(@RequestBody Organizer organizer) {
        Organizer newOrganizer = organizerService.createOrganizer(organizer);
        return ResponseEntity.ok(newOrganizer);
    }

    @GetMapping
    public List<Organizer> getAllOrganizers() {
        return organizerService.findAllOrganizers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organizer> getOrganizerById(@PathVariable Long id) {
        return organizerService.findOrganizerById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organizer> updateOrganizer(@PathVariable Long id, @RequestBody Organizer organizerDetails) {
        Organizer updatedOrganizer = organizerService.updateOrganizer(id, organizerDetails);
        return ResponseEntity.ok(updatedOrganizer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrganizer(@PathVariable Long id) {
        organizerService.deleteOrganizer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Organizer> getOrganizerByEmail(@PathVariable String email) {
        Organizer organizer = organizerService.findByEmail(email);
        if (organizer != null) {
            return ResponseEntity.ok(organizer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}