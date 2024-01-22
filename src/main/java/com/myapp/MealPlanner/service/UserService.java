package com.myapp.MealPlanner.service;

import com.myapp.MealPlanner.Transformer;
import com.myapp.MealPlanner.dto.RegisterRequest;
import com.myapp.MealPlanner.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public RegisterRequest registerUser(RegisterRequest registerRequest){
        var entity = Transformer.fromDto(registerRequest);
        return Transformer.toDto(userRepository.save(entity));
    }
}
