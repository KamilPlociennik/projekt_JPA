package com.capgemini.wsb.mapper;

import com.capgemini.wsb.dto.DoctorTO;
import com.capgemini.wsb.persistence.entity.DoctorEntity;

public class DoctorMapper {

    public static DoctorTO mapToTO(DoctorEntity entity) {
        if (entity == null) {
            return null;
        }

        DoctorTO to = new DoctorTO();
        to.setId(entity.getId());
        to.setFirstName(entity.getFirstName());
        to.setLastName(entity.getLastName());
        to.setEmail(entity.getEmail());
        to.setTelephoneNumber(entity.getTelephoneNumber());
        to.setDoctorNumber(entity.getDoctorNumber());
        to.setSpecialization(entity.getSpecialization());

        return to;
    }

    public static DoctorEntity mapToEntity(DoctorTO to) {
        if (to == null) {
            return null;
        }

        DoctorEntity entity = new DoctorEntity();
        entity.setId(to.getId());
        entity.setFirstName(to.getFirstName());
        entity.setLastName(to.getLastName());
        entity.setEmail(to.getEmail());
        entity.setTelephoneNumber(to.getTelephoneNumber());
        entity.setDoctorNumber(to.getDoctorNumber());
        entity.setSpecialization(to.getSpecialization());

        return entity;
    }
}
