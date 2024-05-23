package com.wsb.dao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.capgemini.wsb.WsbJpaApplication;
import java.time.LocalDate;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.persistence.dao.VisitRepository;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.capgemini.wsb.persistence.dao.PatientRepository;
import com.capgemini.wsb.persistence.entity.PatientEntity;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WsbJpaApplication.class)
public class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private VisitRepository visitRepository;


    @Test
    public void whenFindByLastName_thenReturnPatients() {
        // given
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Alice");
        patient.setLastName("Johnson");
        patient.setAge(30);
        patient.setDateOfBirth(LocalDate.of(1990, 1, 1));
        patient.setTelephoneNumber("111222333");
        patient.setEmail("alice.johnson@example.com");
        patient.setPatientNumber("30");
        patientRepository.save(patient);

        // when
        List<PatientEntity> found = patientRepository.findByLastName("Johnson");

        // then
        assertThat(found.size()).isGreaterThan(0);
        assertThat(found.get(0).getLastName()).isEqualTo("Johnson");
    }
    @Test
    public void whenFindPatientsWithMoreThanXVisits_thenReturnPatients() {
        // given
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Alice");
        patient.setLastName("Johnson");
        patient.setAge(30);
        patient.setDateOfBirth(LocalDate.of(1990, 1, 1));
        patient.setTelephoneNumber("111222333");
        patient.setEmail("alice.johnson@example.com");
        patient.setPatientNumber("30");;
        patientRepository.save(patient);

        VisitEntity visit1 = new VisitEntity();
        visit1.setPatientId(patient.getId());
        visit1.setTime(LocalDateTime.now());
        visitRepository.save(visit1);

        VisitEntity visit2 = new VisitEntity();
        visit2.setPatientId(patient.getId());
        visit2.setTime(LocalDateTime.now());
        visitRepository.save(visit2);

        // when
        List<PatientEntity> found = patientRepository.findPatientsWithMoreThanXVisits(1L);

        // then
        assertThat(found.size()).isGreaterThan(0);
        assertThat(found.get(0).getFirstName()).isEqualTo("Alice");
    }
    @Test
    public void whenFindByAgeGreaterThan_thenReturnPatients() {
        // given
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Alice");
        patient.setLastName("Johnson");
        patient.setAge(35);
        patient.setDateOfBirth(LocalDate.of(1990, 1, 1));
        patient.setTelephoneNumber("111222333");
        patient.setEmail("alice.johnson@example.com");
        patient.setPatientNumber("30");;
        patientRepository.save(patient);

        // when
        List<PatientEntity> found = patientRepository.findByAgeGreaterThan(30);

        // then
        assertThat(found.size()).isGreaterThan(0);
        assertThat(found.get(0).getAge()).isGreaterThan(30);
    }
}

