package com.capgemini.wsb.mapper;

import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.persistence.entity.VisitEntity;

public class VisitMapper {

    public static VisitTO mapToTO(VisitEntity entity) {
        if (entity == null) {
            return null;
        }

        VisitTO to = new VisitTO();
        to.setId(entity.getId());
        to.setDescription(entity.getDescription());
        to.setTime(entity.getTime());
        to.setDoctor(DoctorMapper.mapToTO(entity.getDoctorEntity()));
        to.setPatient(PatientMapper.mapToTO(entity.getPatientEntity()));

        return to;
    }

    public static VisitEntity mapToEntity(VisitTO to) {
        if (to == null) {
            return null;
        }

        VisitEntity entity = new VisitEntity();
        entity.setId(to.getId());
        entity.setDescription(to.getDescription());
        entity.setTime(to.getTime());
        entity.setDoctorEntity(DoctorMapper.mapToEntity(to.getDoctor()));
        entity.setPatientEntity(PatientMapper.mapToEntity(to.getPatient()));

        return entity;
    }
}
