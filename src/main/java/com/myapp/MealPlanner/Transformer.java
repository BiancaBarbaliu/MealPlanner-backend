package com.myapp.MealPlanner;

import com.myapp.MealPlanner.dto.MealPlanDTO;
import com.myapp.MealPlanner.dto.RecipeDTO;
import com.myapp.MealPlanner.dto.RegisterRequest;
import com.myapp.MealPlanner.dto.UserDTO;
import com.myapp.MealPlanner.entity.MealPlanEntity;
import com.myapp.MealPlanner.entity.RecipeEntity;
import com.myapp.MealPlanner.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Transformer {
    private static PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        Transformer.passwordEncoder = passwordEncoder;
    }

    public static RegisterRequest toDto(UserEntity userEntity){
        var dto = new RegisterRequest();
        dto.setUser_id(userEntity.getId());
        dto.setName(userEntity.getName());
        dto.setEmail(userEntity.getEmail());
        dto.setPassword(userEntity.getPassword());
        dto.setDietary_restrictions(userEntity.getDietary_restrictions());
        return dto;
    }

    public static UserDTO toDTO (UserEntity userEntity){
        var dto = new UserDTO();
        dto.setId(userEntity.getId());
        dto.setEmail(userEntity.getEmail());
        dto.setName(userEntity.getName());
        return dto;
    }

    public static UserEntity toEntity(RegisterRequest registerRequest){
        var entity = new UserEntity();
        entity.setId(registerRequest.getUser_id());
        entity.setName(registerRequest.getName());
        entity.setEmail(registerRequest.getEmail());
        entity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        entity.setDietary_restrictions(registerRequest.getDietary_restrictions());
        return entity;
    }

    public static UserEntity toEntity(UserDTO userDTO){
        var entity = new UserEntity();
        entity.setId(userDTO.getId());
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
        dto.setKcalTotal(recipeEntity.getKcalTotal());
        dto.setSearchTerms(recipeEntity.getSearchTerms());
        dto.setPhotoPath(recipeEntity.getPhotoPath());
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
        entity.setKcalTotal(recipeDTO.getKcalTotal());
        entity.setSearchTerms(recipeDTO.getSearchTerms());
        entity.setPhotoPath(recipeDTO.getPhotoPath());
        return entity;
    }

    public static MealPlanDTO toDTO(MealPlanEntity mealPlanEntity){
        var dto = new MealPlanDTO();
        dto.setMeal_plan_id(mealPlanEntity.getMeal_plan_id());
        dto.setUserDTO(toDTO(mealPlanEntity.getUserEntity()));
        dto.setDate(mealPlanEntity.getDate());
        dto.setMeal_type(mealPlanEntity.getMeal_type());
        if (mealPlanEntity.getMealPlanRecipes() != null) {
            List<Long> recipeIds = mealPlanEntity.getMealPlanRecipes().stream()
                    .map(mealPlanRecipe -> mealPlanRecipe.getRecipeEntity().getRecipe_id())
                    .collect(Collectors.toList());
            dto.setRecipeIds(recipeIds);
        }
        return dto;
    }

    public static MealPlanEntity toEntity(MealPlanDTO mealPlanDTO){
        var entity = new MealPlanEntity();
        entity.setMeal_plan_id(mealPlanDTO.getMeal_plan_id());
        entity.setUserEntity(toEntity(mealPlanDTO.getUserDTO()));
        entity.setDate(mealPlanDTO.getDate());
        entity.setMeal_type(mealPlanDTO.getMeal_type());
        return entity;
    }
}
