package com.myapp.MealPlanner.service;

import com.myapp.MealPlanner.Transformer;
import com.myapp.MealPlanner.dto.RegisterRequest;
import com.myapp.MealPlanner.dto.UserDTO;
import com.myapp.MealPlanner.entity.UserEntity;
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
        var entity = Transformer.toEntity(registerRequest);
        return Transformer.toDto(userRepository.save(entity));
    }

    public UserDTO updateUserProfile(Long userId, UserDTO userDTO) throws Exception {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found"));

        userEntity.setDietaryRestriction(userDTO.getDietaryRestriction());

        userRepository.save(userEntity);

        return Transformer.toDTO(userEntity);
    }
}
