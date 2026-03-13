package com.medicore.medicoreapi.infrastructure.persistence.mapper;

import com.medicore.medicoreapi.domain.model.Patient;
import com.medicore.medicoreapi.infrastructure.persistence.entity.PatientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientPersistenceMapper {
    PatientEntity toEntity(Patient patient);
    Patient toDomain(PatientEntity entity);
}
