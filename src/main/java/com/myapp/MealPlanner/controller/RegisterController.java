package com.myapp.MealPlanner.controller;

import com.myapp.MealPlanner.dto.RegisterRequest;
import com.myapp.MealPlanner.entity.UserEntity;
import com.myapp.MealPlanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegisterController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        if(userRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        String encryptedPassword = passwordEncoder.encode(registerRequest.getPassword());
        UserEntity newUser = new UserEntity();
        newUser.setName(registerRequest.getName());
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(encryptedPassword);
        newUser.setDietary_restrictions(registerRequest.getDietary_restrictions());
        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
