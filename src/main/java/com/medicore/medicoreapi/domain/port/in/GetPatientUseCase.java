package com.medicore.medicoreapi.domain.port.in;

import com.medicore.medicoreapi.domain.model.Patient;

import java.util.UUID;

public interface GetPatientUseCase {
    Patient findById(UUID id);
}
