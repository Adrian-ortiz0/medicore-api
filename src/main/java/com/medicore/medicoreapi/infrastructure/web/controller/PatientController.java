package com.medicore.medicoreapi.infrastructure.web.controller;

import com.medicore.medicoreapi.domain.model.Patient;
import com.medicore.medicoreapi.domain.port.in.CreatePatientUseCase;
import com.medicore.medicoreapi.infrastructure.web.dto.request.CreatePatientRequest;
import com.medicore.medicoreapi.infrastructure.web.dto.response.PatientResponse;
import com.medicore.medicoreapi.infrastructure.web.mapper.PatientWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final CreatePatientUseCase createPatientUseCase;
    private final PatientWebMapper mapper;

    public PatientController(CreatePatientUseCase createPatientUseCase, PatientWebMapper mapper) {

        this.createPatientUseCase = createPatientUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<PatientResponse> create(@Valid @RequestBody CreatePatientRequest request) {
        Patient patient = mapper.toDomain(request);
        Patient created = createPatientUseCase.execute(patient);
        PatientResponse response = mapper.toResponse(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
