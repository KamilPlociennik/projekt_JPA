package com.capgemini.wsb.persistence.dao;

import com.capgemini.wsb.persistence.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Repository
public interface PatientRepository extends JpaRepository<PatientEntity,
        Long> {List<PatientEntity> findByLastName(String lastName);
    List<PatientEntity> findByAgeGreaterThan(int age);

    @Query("SELECT p FROM PatientEntity p JOIN p.visits v GROUP BY p.id HAVING COUNT(v.id) > :visitCount")
    List<PatientEntity> findPatientsWithMoreThanXVisits(@Param("visitCount") Long visitCount);
    }


