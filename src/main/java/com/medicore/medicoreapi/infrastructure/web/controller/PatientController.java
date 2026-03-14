package com.medicore.medicoreapi.infrastructure.web.controller;

import com.medicore.medicoreapi.domain.model.Patient;
import com.medicore.medicoreapi.domain.port.in.*;
import com.medicore.medicoreapi.infrastructure.web.dto.request.CreatePatientRequest;
import com.medicore.medicoreapi.infrastructure.web.dto.response.PatientResponse;
import com.medicore.medicoreapi.infrastructure.web.mapper.PatientWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final CreatePatientUseCase createPatientUseCase;
    private final GetPatientUseCase getPatientUseCase;
    private final GetAllPatientsUseCase getAllPatientsUseCase;
    private final UpdatePatientUseCase updatePatientUseCase;
    private final DeletePatientUseCase deletePatientUseCase;
    private final PatientWebMapper mapper;

    public PatientController(CreatePatientUseCase createPatientUseCase,
                             GetPatientUseCase getPatientUseCase,
                             GetAllPatientsUseCase getAllPatientsUseCase,
                             UpdatePatientUseCase updatePatientUseCase,
                             DeletePatientUseCase deletePatientUseCase,
                             PatientWebMapper mapper) {
        this.createPatientUseCase = createPatientUseCase;
        this.getPatientUseCase = getPatientUseCase;
        this.getAllPatientsUseCase = getAllPatientsUseCase;
        this.updatePatientUseCase = updatePatientUseCase;
        this.deletePatientUseCase = deletePatientUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<PatientResponse> create(@Valid @RequestBody CreatePatientRequest request) {
        Patient patient = mapper.toDomain(request);
        Patient created = createPatientUseCase.create(patient);
        PatientResponse response = mapper.toResponse(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> findAll() {
        List<PatientResponse> patients = getAllPatientsUseCase.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> findById(@PathVariable UUID id) {
        Patient patient = getPatientUseCase.findById(id);
        return ResponseEntity.ok(mapper.toResponse(patient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> update(@PathVariable UUID id,
                                                  @Valid @RequestBody CreatePatientRequest request) {
        Patient patient = mapper.toDomain(request);
        Patient updated = updatePatientUseCase.update(id, patient);
        return ResponseEntity.ok(mapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deletePatientUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

}
