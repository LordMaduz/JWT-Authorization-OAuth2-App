package com.jwt.oauth2.controller.reactive;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/reactive-hello")
public class ReactiveHelloController {

    @GetMapping
    public Mono<String> hello(@AuthenticationPrincipal final Jwt jwt) {
        final Map<String, Object> claims = jwt.getClaims();
        return Mono.just("Hello Received with JWT With Claims: %s".formatted(claims.toString()));
    }
}
