package com.medilabo.front.model;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class Note {
    private Integer patientId;
    private LocalDate date;
    private String doctor;
    @NotBlank
    private String content;

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
