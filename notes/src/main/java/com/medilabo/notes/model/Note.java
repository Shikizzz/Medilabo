package com.medilabo.notes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@Document(collection = "notes")
public class Note {
    @Id
    private String id;
    private Patient patient;
    private LocalDate date;
    private String doctor;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id='" + id + '\'' +
                ", patient=" + patient +
                ", date=" + date +
                ", doctor='" + doctor + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
