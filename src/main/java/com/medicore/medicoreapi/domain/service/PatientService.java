package com.medicore.medicoreapi.domain.service;

import com.medicore.medicoreapi.domain.model.Patient;
import com.medicore.medicoreapi.domain.port.in.CreatePatientUseCase;
import com.medicore.medicoreapi.domain.port.out.PatientRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PatientService implements CreatePatientUseCase {

    private final PatientRepositoryPort patientRepositoryPort;

    public PatientService(PatientRepositoryPort patientRepositoryPort){

        this.patientRepositoryPort = patientRepositoryPort;
    }

    @Override
    public Patient execute(Patient patient) {
        if(patientRepositoryPort.existsByEmail(patient.getEmail())){
            throw new IllegalArgumentException("Email already registered");
        }
        if(patientRepositoryPort.existsByDocumentNumber(patient.getDocumentNumber())){
            throw new IllegalArgumentException("Documento number already registered");
        }
        patient.setId(UUID.randomUUID());
        patient.setCreatedAt(LocalDateTime.now());
        patient.setUpdatedAt(LocalDateTime.now());

        return patientRepositoryPort.save(patient);
    }
}
