package com.example.signupapi.controller;

import com.example.signupapi.dto.SignupRequest;
import com.example.signupapi.service.SignupService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final SignupService signupService;

    public AuthController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signup(@Valid @RequestBody SignupRequest request) {
        signupService.registerUser(request);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Signup successful");
        response.put("email", request.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}