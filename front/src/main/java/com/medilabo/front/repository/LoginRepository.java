package com.medilabo.front.repository;

import com.medilabo.front.model.LoginForm;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@Repository
public class LoginRepository {
    private final String apiUrl;

    public LoginRepository(CustomProperties props) {
        this.apiUrl=props.getGatewayURL()+"/auth/login";
    }

    public String postLogin(LoginForm form){
        WebClient client = WebClient.builder()
                .baseUrl(apiUrl)
                .build();
        return client.post()
                .body(BodyInserters.fromValue(form))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
