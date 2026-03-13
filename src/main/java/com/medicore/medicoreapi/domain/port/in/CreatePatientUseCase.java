package com.medicore.medicoreapi.domain.port.in;

import com.medicore.medicoreapi.domain.model.Patient;

public interface CreatePatientUseCase {
    Patient execute(Patient patient);
}
