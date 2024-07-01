package com.medilabo.front.service;

import com.medilabo.front.model.Patient;
import com.medilabo.front.model.PatientDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class PatientService {
    private final String apiUrl = "http://localhost:8080/patient";


 /*   public RestTemplate setRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        return  restTemplate;
    }*/

    public Patient getPatientById(Integer id){
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "?id=" + id;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Patient> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, Patient.class);
        return response.getBody();
    }

    public List<Patient> getPatientsListBySearchedString(String search){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<List<Patient>> response =
                restTemplate.exchange(apiUrl + "/search?search="+search,
                        HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Patient>>() {
                        });
        List<Patient> patients = response.getBody();
        return patients;
    }

    public void postPatient(PatientDTO patient) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<PatientDTO> request = new HttpEntity<PatientDTO>(patient, headers);
        restTemplate.postForEntity(apiUrl, request, String.class);
        }

    public void putPatient(PatientDTO patient) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<PatientDTO> request = new HttpEntity<PatientDTO>(patient, headers);
        restTemplate.exchange(apiUrl, HttpMethod.PUT, request, String.class);
    }

    public void deletePatient(Integer id){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String url = apiUrl + "?id=" + id;
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
    }

}

