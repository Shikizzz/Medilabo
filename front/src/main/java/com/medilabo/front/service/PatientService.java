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

    public Patient getPatientById(Integer id, String token){
        return patientRepository.getPatientById(id, token);
    }

    public List<Patient> getPatientsListBySearchedString(String search, String token){
        return patientRepository.getPatientsListBySearchedString(search, token);
    }

    public void postPatient(PatientDTO patient, String token) throws IOException {
        patientRepository.postPatient(patient, token);
        }

    public void putPatient(PatientDTO patient, String token) throws IOException {
        patientRepository.putPatient(patient, token);
    }

    public void deletePatient(Integer id, String token){
        patientRepository.deletePatient(id, token);
    }

}

