package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/get-token")
    public Mono<String> getToken() {
        return tokenService.getAccessToken();
    }
}
