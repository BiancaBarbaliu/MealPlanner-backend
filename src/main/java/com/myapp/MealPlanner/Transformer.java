package com.myapp.MealPlanner;

import com.myapp.MealPlanner.dto.RegisterRequest;
import com.myapp.MealPlanner.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class Transformer {
    private static PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        Transformer.passwordEncoder = passwordEncoder;
    }

    public static RegisterRequest toDto(UserEntity userEntity){
        var dto = new RegisterRequest();
        dto.setUser_id(userEntity.getUser_id());
        dto.setName(userEntity.getName());
        dto.setEmail(userEntity.getEmail());
        dto.setPassword(userEntity.getPassword());
        dto.setDietary_restrictions(userEntity.getDietary_restrictions());
        return dto;
    }

    public static UserEntity fromDto(RegisterRequest registerRequest){
        var entity = new UserEntity();
        entity.setUser_id(registerRequest.getUser_id());
        entity.setName(registerRequest.getName());
        entity.setEmail(registerRequest.getEmail());
        entity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        entity.setDietary_restrictions(registerRequest.getDietary_restrictions());
        return entity;
    }
}
