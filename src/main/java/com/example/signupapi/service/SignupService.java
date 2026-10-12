package com.example.signupapi.service;

import com.example.signupapi.dto.SignupRequest;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    public void registerUser(SignupRequest request) {
        // In real app, save to DB here
        // Example:
        // userRepository.save(...)
        System.out.println("User registered: " + request.getEmail());
    }
}