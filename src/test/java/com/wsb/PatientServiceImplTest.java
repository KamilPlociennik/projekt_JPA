package com.wsb;

import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.persistence.dao.DoctorRepository;
import com.capgemini.wsb.persistence.dao.PatientRepository;
import com.capgemini.wsb.persistence.dao.VisitRepository;
import com.capgemini.wsb.WsbJpaApplication;
import com.capgemini.wsb.service.PatientService;
import com.capgemini.wsb.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.capgemini.wsb.persistence.enums.TreatmentType;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = WsbJpaApplication.class)
public class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private VisitRepository visitRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private PatientServiceImpl patientServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Transactional
    public void testDeletePatientAndCascadingVisits() {
        Long patientId = 1L;

        // Create mock patient and visits
        PatientEntity patient = new PatientEntity();
        patient.setId(patientId);

        VisitEntity visit1 = new VisitEntity();
        visit1.setPatientEntity(patient);

        VisitEntity visit2 = new VisitEntity();
        visit2.setPatientEntity(patient);

        List<VisitEntity> visits = Arrays.asList(visit1, visit2);

        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));
        when(visitRepository.findByPatientEntityId(patientId)).thenReturn(visits);

        // Perform delete operation
        patientServiceImpl.deletePatient(patientId);

        // Verify that visits and patient are deleted
        verify(visitRepository, times(1)).deleteAll(visits);
        verify(patientRepository, times(1)).deleteById(patientId);

        // Verify that doctors are not deleted
        verify(doctorRepository, never()).deleteAll();
    }

    @Test
    public void testGetPatientById() {
        Long patientId = 1L;
        PatientEntity patient = new PatientEntity();
        patient.setId(patientId);
        patient.setFirstName("Alice");
        patient.setLastName("Johnson");
        patient.setEmail("alice.johnson@example.com");
        patient.setTelephoneNumber("111222333");
        patient.setPatientNumber("P123");
        patient.setAge(30);
        patient.setDateOfBirth(LocalDate.of(1993, 1, 1));

        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));

        PatientTO result = patientServiceImpl.getPatientById(patientId);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(patientId);
        assertThat(result.getFirstName()).isEqualTo("Alice");
        assertThat(result.getLastName()).isEqualTo("Johnson");
        assertThat(result.getEmail()).isEqualTo("alice.johnson@example.com");
        assertThat(result.getTelephoneNumber()).isEqualTo("111222333");
        assertThat(result.getPatientNumber()).isEqualTo("P123");
        assertThat(result.getAge()).isEqualTo(30);
        assertThat(result.getDateOfBirth()).isEqualTo(LocalDate.of(1993, 1, 1));
    }
}
