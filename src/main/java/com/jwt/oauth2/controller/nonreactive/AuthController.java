package com.jwt.oauth2.controller.nonreactive;

import com.jwt.oauth2.util.JWTTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final JWTTokenProvider tokenProvider;

    @PostMapping("/login")
    public String login(@RequestBody Map<String,Object> claims) {
        return tokenProvider.generateToken(claims);
    }

}
