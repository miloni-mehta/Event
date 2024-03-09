package com.example.event.repository;

import com.example.event.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {
    List<Register> findByUserId(Long userId);

    List<Register> findByEventId(Long eventId);

    List<Register> findByAdminId(Long adminId);

    List<Register> findByOrganizerId(Long organizerId);
}