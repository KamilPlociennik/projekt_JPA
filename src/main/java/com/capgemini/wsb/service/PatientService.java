package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.PatientTO;
import java.util.List;
import com.capgemini.wsb.persistence.entity.VisitEntity;

public interface PatientService {
    PatientTO findById(Long id);
    List<PatientTO> findAll();
    PatientTO save(PatientTO patient);
    void delete(Long id);
    void deletePatient(Long patientId);
    PatientTO getPatientById(Long patientId);

    List<VisitEntity> findVisitsByPatientId(Long patientId);
}
