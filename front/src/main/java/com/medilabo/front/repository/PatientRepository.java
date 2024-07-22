package com.medilabo.front.repository;

import com.medilabo.front.model.Patient;
import com.medilabo.front.model.PatientDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.List;

@Repository
public class PatientRepository {

    private final String apiUrl;

    public PatientRepository(CustomProperties props) {
        this.apiUrl=props.getGatewayURL()+"/patient";
    }

    public Patient getPatientById(Integer id){
        String url = apiUrl + "?id=" + id;
        WebClient client = WebClient.builder()
                .baseUrl(url)
                .build();
        return client.get()
                .retrieve()
                .bodyToMono(Patient.class)
                .block();
    }

    public List<Patient> getPatientsListBySearchedString(String search){
        String url = apiUrl + "/search?search="+search;
        WebClient client = WebClient.builder()
                .baseUrl(url)
                .build();
        return client.get()
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Patient>>(){})
                .block();
    }

    public void postPatient(PatientDTO patient) throws IOException {
        WebClient client = WebClient.builder()
                .baseUrl(apiUrl)
                .build();
        String response = client.post()
                .body(BodyInserters.fromValue(patient))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public void putPatient(PatientDTO patient) throws IOException {
        WebClient client = WebClient.builder()
                .baseUrl(apiUrl)
                .build();
        String response = client.put()
                .body(BodyInserters.fromValue(patient))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public void deletePatient(Integer id){
        String url = apiUrl + "?id=" + id;
        WebClient client = WebClient.builder()
                .baseUrl(url)
                .build();
        String response = client.delete()
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
