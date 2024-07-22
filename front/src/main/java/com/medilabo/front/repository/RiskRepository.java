package com.medilabo.front.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class RiskRepository {
    private final String apiUrl;

    public RiskRepository(CustomProperties props) {
        this.apiUrl=props.getGatewayURL()+"/risk";
    }

    public String getRisk(Integer id){
        String url = apiUrl+"?id="+id.toString();
        WebClient client = WebClient.builder()
                .baseUrl(url)
                .build();
        return client.get()
                .retrieve()
                .bodyToMono( String.class)
                .block();
    }
}
