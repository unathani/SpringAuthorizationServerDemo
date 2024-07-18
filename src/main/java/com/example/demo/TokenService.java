package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TokenService {

    @Autowired
    private WebClient webClient;

    public Mono<String> getAccessToken() {
        return webClient.post()
                .uri("http://localhost:9000/oauth2/token")
                .headers(headers -> headers.setBasicAuth("client-id", "client-secret"))
                .body(BodyInserters.fromFormData("grant_type", "client_credentials"))
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    // Assuming response is a JSON string. Parse it to extract the token.
                    // Here, just returning the whole response for simplicity.
                    return response;
                });
    }
}
