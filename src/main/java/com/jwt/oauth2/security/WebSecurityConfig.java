
package com.jwt.oauth2.security;

import com.jwt.oauth2.config.ApplicationJwtConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

//When Testing Non-Reactive Flow Comment the @ReactiveWebSecurityConfig class defined for Reactive Approach
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final ApplicationJwtConfig applicationJwtConfig;

    private static final String[] PERMITTED_PATTERNS = {
            "/auth/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(PERMITTED_PATTERNS).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(configure-> configure.sessionCreationPolicy(SessionCreationPolicy.NEVER))
                .oauth2ResourceServer((configure) -> configure
                        .jwt(Customizer.withDefaults())
                ).build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(applicationJwtConfig.getPublicKey()).build();
    }

}
