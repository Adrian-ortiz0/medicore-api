package com.medicore.medicoreapi.domain.service;

import com.medicore.medicoreapi.domain.model.Patient;
import com.medicore.medicoreapi.domain.port.in.*;
import com.medicore.medicoreapi.domain.port.out.PatientRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService implements
        CreatePatientUseCase,
        GetPatientUseCase,
        GetAllPatientsUseCase,
        UpdatePatientUseCase,
        DeletePatientUseCase {

    private final PatientRepositoryPort patientRepositoryPort;

    public PatientService(PatientRepositoryPort patientRepositoryPort) {
        this.patientRepositoryPort = patientRepositoryPort;
    }

    @Override
    public Patient create(Patient patient) {
        if (patientRepositoryPort.existsByEmail(patient.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }
        if (patientRepositoryPort.existsByDocumentNumber(patient.getDocumentNumber())) {
            throw new IllegalArgumentException("Document number already registered");
        }
        patient.setId(UUID.randomUUID());
        patient.setCreatedAt(LocalDateTime.now());
        patient.setUpdatedAt(LocalDateTime.now());
        return patientRepositoryPort.save(patient);
    }

    @Override
    public Patient findById(UUID id) {
        return patientRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
    }

    @Override
    public List<Patient> findAll() {
        return patientRepositoryPort.findAll();
    }

    @Override
    public Patient update(UUID id, Patient patient) {
        Patient existing = patientRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        existing.setFirstName(patient.getFirstName());
        existing.setLastName(patient.getLastName());
        existing.setPhone(patient.getPhone());
        existing.setBirthdate(patient.getBirthdate());
        existing.setUpdatedAt(LocalDateTime.now());

        return patientRepositoryPort.save(existing);
    }

    @Override
    public void delete(UUID id) {
        if (!patientRepositoryPort.existsById(id)) {
            throw new IllegalArgumentException("Patient not found");
        }
        patientRepositoryPort.deleteById(id);
    }
}