package com.medilabo.info.repository;

import com.medilabo.info.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer>/*, JpaSpecificationExecutor<Patient>*/ {
    Optional<Patient> findByFirstnameAndLastnameAndBirthdate(String firstname, String lastname, LocalDate birthdate);
    List<Patient> findByFirstnameContainingOrLastnameContaining(String firstname, String lastname);

}
