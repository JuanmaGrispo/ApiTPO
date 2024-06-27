package com.apiTPO.technologyHouse.app.controllers;

import com.apiTPO.technologyHouse.app.auth.AuthenticationRequest;
import com.apiTPO.technologyHouse.app.auth.AuthenticationResponse;
import com.apiTPO.technologyHouse.app.auth.RegisterRequest;
import com.apiTPO.technologyHouse.app.dtos.UserIdDTO;
import com.apiTPO.technologyHouse.app.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request) {
        AuthenticationResponse response = service.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}