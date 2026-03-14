package com.medicore.medicoreapi.domain.port.in;

import com.medicore.medicoreapi.domain.model.Patient;

import java.util.List;

public interface GetAllPatientsUseCase {
    List<Patient> findAll();
}
