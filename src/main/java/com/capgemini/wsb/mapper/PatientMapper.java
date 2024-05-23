package com.capgemini.wsb.mapper;

import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import java.util.stream.Collectors;

public class PatientMapper {

    public static PatientTO mapToTO(PatientEntity entity) {
        if (entity == null) {
            return null;
        }

        PatientTO to = new PatientTO();
        to.setId(entity.getId());
        to.setFirstName(entity.getFirstName());
        to.setLastName(entity.getLastName());
        to.setEmail(entity.getEmail());
        to.setTelephoneNumber(entity.getTelephoneNumber());
        to.setPatientNumber(entity.getPatientNumber());
        to.setAge(entity.getAge());
        to.setVisits(entity.getVisits().stream()
                .map(VisitMapper::mapToTO)
                .collect(Collectors.toList()));

        return to;
    }

    public static PatientEntity mapToEntity(PatientTO to) {
        if (to == null) {
            return null;
        }

        PatientEntity entity = new PatientEntity();
        entity.setId(to.getId());
        entity.setFirstName(to.getFirstName());
        entity.setLastName(to.getLastName());
        entity.setEmail(to.getEmail());
        entity.setTelephoneNumber(to.getTelephoneNumber());
        entity.setPatientNumber(to.getPatientNumber());
        entity.setAge(to.getAge());
        entity.setVisits(to.getVisits().stream()
                .map(VisitMapper::mapToEntity)
                .collect(Collectors.toList()));

        return entity;
    }
}
