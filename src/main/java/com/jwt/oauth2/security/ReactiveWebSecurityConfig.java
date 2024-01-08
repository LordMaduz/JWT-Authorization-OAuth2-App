package com.jwt.oauth2.security;

import com.jwt.oauth2.config.ApplicationJwtConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

//When Testing Non-Reactive Flow Comment the @WebSecurityConfig class defined for Non-Reactive Approach
@Configuration
@RequiredArgsConstructor
public class ReactiveWebSecurityConfig {

    private final ApplicationJwtConfig applicationJwtConfig;
    private static final String[] PERMITTED_PATTERNS = {
            "/reactive-auth/**"
    };

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange((authorize) -> authorize
                        .pathMatchers(PERMITTED_PATTERNS).permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer((configurer) -> configurer
                        .jwt(Customizer.withDefaults()))
                .build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return NimbusReactiveJwtDecoder.withPublicKey(this.applicationJwtConfig.getPublicKey()).build();
    }
}
