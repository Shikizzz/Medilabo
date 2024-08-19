package com.medilabo.risk.repository;

import com.medilabo.risk.model.Note;
import com.medilabo.risk.model.Patient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Repository
public class PatientRepository {
    private final String patientURL;
    private final String noteURL;

    public PatientRepository(CustomProperties props){
        this.patientURL = props.getPatientURL();
        this.noteURL = props.getNoteURL();
    }

    public Patient getPatient(Integer id, String token){
        String url = patientURL+"?id="+id.toString();
        WebClient client = WebClient.builder()
                .baseUrl(url)
                .build();
        return client.get()
                .headers(h -> h.setBearerAuth(token))
                .retrieve()
                .bodyToMono( Patient.class)
                .block();
        }

    public List<Note> getNotes(Integer patientId, String token){
        String url = noteURL+"?id="+patientId.toString();
        WebClient client = WebClient.builder()
                .baseUrl(url)
                .build();
        return client.get()
                .headers(h -> h.setBearerAuth(token))
                .retrieve()
                .bodyToMono( new ParameterizedTypeReference<List<Note>>(){})
                .block();
    }

}
