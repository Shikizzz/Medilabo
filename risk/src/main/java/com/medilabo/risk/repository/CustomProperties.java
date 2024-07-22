package com.medilabo.risk.repository;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "medilabo")
public class CustomProperties {
    private String patientURL;
    private String noteURL;

    public String getPatientURL() {
        return patientURL;
    }
    public void setPatientURL(String patientURL) {
        this.patientURL = patientURL;
    }
    public String getNoteURL() {
        return noteURL;
    }
    public void setNoteURL(String noteURL) {
        this.noteURL = noteURL;
    }
}
