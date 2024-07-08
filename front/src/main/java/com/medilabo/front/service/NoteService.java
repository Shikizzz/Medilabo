package com.medilabo.front.service;

import com.medilabo.front.config.LoggingRequestInterceptor;
import com.medilabo.front.model.*;
import com.medilabo.front.repository.NoteRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    private NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public List<Note> getNotesByPatientId(Integer patientId){
        return repository.getNotesByPatientId(patientId);
    }


    public void postNote(Note note) throws IOException {
        repository.postNote(note);
    }
}
