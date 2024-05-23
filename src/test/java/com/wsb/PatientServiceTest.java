package com.wsb;
import com.capgemini.wsb.WsbJpaApplication;
import com.capgemini.wsb.persistence.enums.TreatmentType;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.capgemini.wsb.persistence.entity.MedicalTreatmentEntity;
import com.capgemini.wsb.persistence.dao.PatientRepository;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.service.PatientService;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.persistence.dao.VisitRepository;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WsbJpaApplication.class)
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void whenFindVisitsByPatientId_thenReturnVisits() {
        // given
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Alice");
        patient.setLastName("Johnson");
        patient.setAge(30);
        patient.setDateOfBirth(LocalDate.of(1990, 1, 1));
        patient.setPatientNumber("123456789");
        patient.setEmail("alice.johnson@example.com");
        patient.setPatientNumber("30");
        patient.setTelephoneNumber("111222333");
        patientRepository.save(patient);


        VisitEntity visit = new VisitEntity();
        visit.setDescription("Routine check-up");
        visit.setTime(LocalDateTime.now());
        visit.setPatientEntity(patient);
        visit.setId(1L);

        MedicalTreatmentEntity treatment = new MedicalTreatmentEntity();
        treatment.setDescription("Test treatment");
        treatment.setType(TreatmentType.USG);
        treatment.setVisit(visit);

        List<MedicalTreatmentEntity> treatments = new ArrayList<>();
        treatments.add(treatment);
        visit.setTreatments(treatments);
        visitRepository.save(visit);

        // when
        List<VisitEntity> visits = patientService.findVisitsByPatientId(patient.getId());

        // then
        assertThat(visits.size()).isGreaterThan(0);
        assertThat(visits.get(0).getId()).isEqualTo(visit.getId());
        assertThat(visits.get(0).getTreatments()).isNotEmpty();
        assertThat(visits.get(0).getTreatments().get(0).getDescription()).isEqualTo("Test treatment");
    }
}

