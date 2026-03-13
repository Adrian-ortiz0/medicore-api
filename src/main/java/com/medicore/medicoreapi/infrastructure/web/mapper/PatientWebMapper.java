package com.medicore.medicoreapi.infrastructure.web.mapper;

import com.medicore.medicoreapi.domain.model.Patient;
import com.medicore.medicoreapi.infrastructure.web.dto.request.CreatePatientRequest;
import com.medicore.medicoreapi.infrastructure.web.dto.response.PatientResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientWebMapper {
    Patient toDomain(CreatePatientRequest request);
    PatientResponse toResponse(Patient patient);
}
