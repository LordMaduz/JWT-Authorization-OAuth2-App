/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jwt.oauth2.controller.reactive;

import com.jwt.oauth2.util.JWTTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/reactive-auth")
@RequiredArgsConstructor
public class ReactiveAuthController {

    private final JWTTokenProvider tokenProvider;

    @PostMapping("/login")
    public Mono<String> login(@RequestBody Mono<Map<String, Object>> claimsMono) {
        return claimsMono.flatMap(claims -> Mono.fromCallable(() -> tokenProvider.generateToken(claims)));
    }

}
