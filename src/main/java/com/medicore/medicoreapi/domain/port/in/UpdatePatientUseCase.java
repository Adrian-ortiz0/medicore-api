package com.medicore.medicoreapi.domain.port.in;

import com.medicore.medicoreapi.domain.model.Patient;

import java.util.UUID;

public interface UpdatePatientUseCase {
    Patient update(UUID id, Patient patient);
}
