package com.example.event.service;

import com.example.event.model.Organizer;
import com.example.event.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class OrganizerService {

    private final OrganizerRepository organizerRepository;

    @Autowired
    public OrganizerService(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }

    public Organizer createOrganizer(Organizer organizer) {
        return organizerRepository.save(organizer);
    }

    public List<Organizer> findAllOrganizers() {
        return organizerRepository.findAll();
    }

    public Optional<Organizer> findOrganizerById(Long id) {
        return organizerRepository.findById(id);
    }

    public Organizer updateOrganizer(Long id, Organizer organizerDetails) {
        return organizerRepository.findById(id).map(organizer -> {
            organizer.setName(organizerDetails.getName());
            organizer.setEmail(organizerDetails.getEmail());
            // Update additional fields here
            return organizerRepository.save(organizer);
        }).orElseGet(() -> {
            organizerDetails.setId(id);
            return organizerRepository.save(organizerDetails);
        });
    }

    public void deleteOrganizer(Long id) {
        organizerRepository.deleteById(id);
    }

    public Organizer findByEmail(String email) {
        return organizerRepository.findByEmail(email);
    }
}