package com.medicore.medicoreapi.infrastructure.persistence.repository;

import com.medicore.medicoreapi.domain.model.Patient;
import com.medicore.medicoreapi.domain.port.out.PatientRepositoryPort;
import com.medicore.medicoreapi.infrastructure.persistence.entity.PatientEntity;
import com.medicore.medicoreapi.infrastructure.persistence.mapper.PatientPersistenceMapper;
import org.springframework.stereotype.Component;

@Component
public class PatientRepositoryImpl implements PatientRepositoryPort {

    private final PatientJpaRepository jpaRepository;
    private final PatientPersistenceMapper mapper;

    public PatientRepositoryImpl(PatientJpaRepository jpaRepository, PatientPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Patient save(Patient patient) {
        PatientEntity entity = mapper.toEntity(patient);
        PatientEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return jpaRepository.existsByDocumentNumber(documentNumber);
    }
}
