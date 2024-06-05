package com.medilabo.info.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDate;

public class PatientDTO {
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String genre;
    private String address;
    private String phoneNumber;

    public static Patient convertToPatient(PatientDTO patientDTO){
        Patient patient = new Patient();
        patient.setFirstname(patientDTO.getFirstname());
        patient.setLastname(patientDTO.getLastname());
        patient.setBirthdate(patientDTO.getBirthdate());
        switch (patientDTO.getGenre()){
            case "MALE":
                patient.setGenre(Genre.MALE);
                break;
            case "FEMALE":
                patient.setGenre(Genre.FEMALE);
                break;
            case "OTHER":
                patient.setGenre(Genre.OTHER);
                break;
            default:
                patient.setGenre(Genre.UNREADABLE);
                break;
        }
        patient.setAddress(patientDTO.getAddress());
        patient.setPhoneNumber(patientDTO.getPhoneNumber());
        return patient;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
