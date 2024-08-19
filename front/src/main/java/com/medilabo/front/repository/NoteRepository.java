package com.medilabo.front.repository;

import com.medilabo.front.model.Note;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.List;

@Repository
public class NoteRepository {
    private final String apiUrl;

    public NoteRepository(CustomProperties props) {
        this.apiUrl=props.getGatewayURL()+"/note";
    }

    public List<Note> getNotesByPatientId(Integer patientId, String token){
        String url = apiUrl+"?id="+patientId.toString();
        WebClient client = WebClient.builder()
                .baseUrl(url)
                .build();
        return client.get()
                .headers(h -> h.setBearerAuth(token))
                .retrieve()
                .bodyToMono( new ParameterizedTypeReference<List<Note>>(){})
                .block();
    }

    public void postNote(Note note, String token) throws IOException {
        WebClient client = WebClient.builder()
                .baseUrl(apiUrl)
                .build();
        String response = client.post()
                .headers(h -> h.setBearerAuth(token))
                .body(BodyInserters.fromValue(note))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
