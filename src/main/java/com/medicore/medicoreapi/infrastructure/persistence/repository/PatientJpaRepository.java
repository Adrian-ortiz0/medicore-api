package com.medicore.medicoreapi.infrastructure.persistence.repository;

import com.medicore.medicoreapi.infrastructure.persistence.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientJpaRepository extends JpaRepository<PatientEntity, UUID> {
    boolean existsByEmail(String email);
    boolean existsByDocumentNumber(String documentNumber);
}
