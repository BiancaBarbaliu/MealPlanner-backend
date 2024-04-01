package com.myapp.MealPlanner.controller;

import com.myapp.MealPlanner.dto.RegisterRequest;
import com.myapp.MealPlanner.repository.UserRepository;
import com.myapp.MealPlanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    private final UserService userService;

    public RegisterController(UserService userService){
        this.userService = userService;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        RegisterRequest newUser = userService.registerUser(registerRequest);
        Long userId = newUser.getUser_id();
        Map<String, Long> response = Collections.singletonMap("userId", userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
