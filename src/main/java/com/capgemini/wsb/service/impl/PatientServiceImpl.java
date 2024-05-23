package com.capgemini.wsb.service.impl;

import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.mapper.PatientMapper;
import com.capgemini.wsb.persistence.dao.PatientRepository;
import com.capgemini.wsb.persistence.dao.VisitRepository;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capgemini.wsb.persistence.dao.VisitRepository;
import com.capgemini.wsb.persistence.enums.TreatmentType;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private VisitRepository visitRepository;


    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, VisitRepository visitRepository) {
        this.patientRepository = patientRepository;
        this.visitRepository = visitRepository;
    }

    @Override
    public PatientTO findById(Long id) {
        return PatientMapper.mapToTO(patientRepository.findById(id).orElse(null));
    }

    @Override
    public List<PatientTO> findAll() {
        return patientRepository.findAll().stream()
                .map(PatientMapper::mapToTO)
                .collect(Collectors.toList());
    }

    @Override
    public PatientTO save(PatientTO patient) {
        return PatientMapper.mapToTO(patientRepository.save(PatientMapper.mapToEntity(patient)));
    }

    @Override
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deletePatient(Long patientId) {
        List<VisitEntity> visits = visitRepository.findByPatientEntityId(patientId);
        visitRepository.deleteAll(visits);
        patientRepository.deleteById(patientId);
    }

    @Override
    public PatientTO getPatientById(Long patientId) {
        Optional<PatientEntity> patientEntityOpt = patientRepository.findById(patientId);
        if (patientEntityOpt.isPresent()) {
            PatientEntity patient = patientEntityOpt.get();
            PatientTO patientTO = new PatientTO();
            patientTO.setId(patient.getId());
            patientTO.setFirstName(patient.getFirstName());
            patientTO.setLastName(patient.getLastName());
            patientTO.setEmail(patient.getEmail());
            patientTO.setTelephoneNumber(patient.getTelephoneNumber());
            patientTO.setPatientNumber(patient.getPatientNumber());
            patientTO.setDateOfBirth(patient.getDateOfBirth());
            patientTO.setAge(patient.getAge());
            return patientTO;
        }
            return null;
        }
    @Override
    public List<VisitEntity> findVisitsByPatientId(Long patientId) {
        return visitRepository.findByPatientEntityId(patientId);
    }
    }

