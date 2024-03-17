package com.myapp.MealPlanner;

import com.myapp.MealPlanner.dto.RecipeDTO;
import com.myapp.MealPlanner.dto.RegisterRequest;
import com.myapp.MealPlanner.entity.RecipeEntity;
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

    public static UserEntity toEntity(RegisterRequest registerRequest){
        var entity = new UserEntity();
        entity.setUser_id(registerRequest.getUser_id());
        entity.setName(registerRequest.getName());
        entity.setEmail(registerRequest.getEmail());
        entity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        entity.setDietary_restrictions(registerRequest.getDietary_restrictions());
        return entity;
    }

    public static RecipeDTO toDto(RecipeEntity recipeEntity){
        var dto = new RecipeDTO();
        dto.setRecipe_id(recipeEntity.getRecipe_id());
        dto.setName(recipeEntity.getName());
        dto.setDescription(recipeEntity.getDescription());
        dto.setIngredients(recipeEntity.getIngredients());
        dto.setIngredients_raw(recipeEntity.getIngredients_raw());
        dto.setInstructions(recipeEntity.getInstructions());
        dto.setServings(recipeEntity.getServings());
        dto.setServing_size(recipeEntity.getServing_size());
        return dto;
    }

    public static RecipeEntity toEntity(RecipeDTO recipeDTO){
        var entity = new RecipeEntity();
        entity.setRecipe_id(recipeDTO.getRecipe_id());
        entity.setName(recipeDTO.getName());
        entity.setDescription(recipeDTO.getDescription());
        entity.setIngredients(recipeDTO.getIngredients());
        entity.setIngredients_raw(recipeDTO.getIngredients_raw());
        entity.setInstructions(recipeDTO.getInstructions());
        entity.setServings(recipeDTO.getServings());
        entity.setServing_size(recipeDTO.getServing_size());
        return entity;
    }
}
