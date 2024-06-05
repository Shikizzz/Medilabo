package com.medilabo.front.service;

import com.medilabo.front.model.Patient;
import com.medilabo.front.model.PatientDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;


@Service
public class PatientService {
    private final String apiUrl = "http://localhost:8080/patient";

    public Patient getPatientById(Integer id){
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "?id=" + id;
        return restTemplate.getForObject(url, Patient.class);
    }

    public List<Patient> getPatientsListBySearchedString(String search){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Patient>> response =
                restTemplate.exchange(apiUrl + "/search?search="+search,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Patient>>() {
                        });
        List<Patient> patients = response.getBody();
        return patients;
    }

    public void postPatient(PatientDTO patient) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<PatientDTO> request = new HttpEntity<PatientDTO>(patient, headers);
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(apiUrl, request, String.class);
        }

    public void putPatient(PatientDTO patient) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<PatientDTO> request = new HttpEntity<PatientDTO>(patient, headers);
        ResponseEntity<String> responseEntityStr = restTemplate.exchange(apiUrl, HttpMethod.PUT, request, String.class);
    }

    public void deletePatient(Integer id){
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "?id=" + id;
        restTemplate.delete(url);
    }

}

