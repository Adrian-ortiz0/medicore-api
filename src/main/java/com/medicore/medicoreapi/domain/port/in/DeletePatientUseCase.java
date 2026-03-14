package com.medicore.medicoreapi.domain.port.in;

import java.util.UUID;

public interface DeletePatientUseCase {
    void delete(UUID id);
}
