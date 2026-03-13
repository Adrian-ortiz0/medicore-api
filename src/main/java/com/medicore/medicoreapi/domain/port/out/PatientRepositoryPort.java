package com.medicore.medicoreapi.domain.port.out;

import com.medicore.medicoreapi.domain.model.Patient;

public interface PatientRepositoryPort {
    Patient save(Patient patient);
    boolean existsByEmail(String email);
    boolean existsByDocumentNumber(String documentNumber);
}
