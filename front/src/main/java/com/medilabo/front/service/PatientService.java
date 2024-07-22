package com.medilabo.front.service;

import com.medilabo.front.model.Patient;
import com.medilabo.front.model.PatientDTO;
import com.medilabo.front.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient getPatientById(Integer id){
        return patientRepository.getPatientById(id);
    }

    public List<Patient> getPatientsListBySearchedString(String search){
        return patientRepository.getPatientsListBySearchedString(search);
    }

    public void postPatient(PatientDTO patient) throws IOException {
        patientRepository.postPatient(patient);
        }

    public void putPatient(PatientDTO patient) throws IOException {
        patientRepository.putPatient(patient);
    }

    public void deletePatient(Integer id){
        patientRepository.deletePatient(id);
    }

}

