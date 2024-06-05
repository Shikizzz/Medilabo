package com.medilabo.info.service;

import com.medilabo.info.model.Patient;
import com.medilabo.info.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private PatientRepository repository;
    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public boolean createPatient(Patient patient){
        if(repository.findByFirstnameAndLastnameAndBirthdate(patient.getFirstname(), patient.getLastname(), patient.getBirthdate()).isEmpty()){
            repository.save(patient);
            return true;
        }
        else{
            return false;
        }
    }
    public Patient readPatient(int id){
        Optional<Patient> patient = repository.findById(id);
        if(patient.isPresent())
            return patient.get();
        else {
            return null;
        }
    }

    public List<Patient> searchPatient(String search){
        return repository.findByFirstnameContainingOrLastnameContaining(search, search);
    }

    public boolean updatePatient(Patient patient){
        Optional<Patient> dbPatient = repository.findByFirstnameAndLastnameAndBirthdate(patient.getFirstname(), patient.getLastname(), patient.getBirthdate());
        if(dbPatient.isPresent()){
            Patient updatedPatient = dbPatient.get();
            updatedPatient.setLastname(patient.getLastname());
            updatedPatient.setFirstname(patient.getFirstname());
            updatedPatient.setBirthdate(patient.getBirthdate());
            updatedPatient.setGenre(patient.getGenre());
            updatedPatient.setAddress(patient.getAddress());
            updatedPatient.setPhoneNumber(patient.getPhoneNumber());
            repository.save(updatedPatient);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean deletePatient(int id){
        try {
            repository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
