package com.myapp.MealPlanner.controller;

import com.myapp.MealPlanner.dto.LoginRequest;
import com.myapp.MealPlanner.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value ="/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){

        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authenticate);

            return ResponseEntity.ok(Collections.singletonMap("message", "User authenticated successfully!"));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Authentication failed: " + e.getMessage());
        }
    }

}
