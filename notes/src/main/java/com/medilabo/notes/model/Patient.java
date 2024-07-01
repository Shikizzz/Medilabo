package com.medilabo.notes.model;

import java.time.LocalDate;

public class Patient {

    private String lastname;
    private String firstname;
    private LocalDate birthdate;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
