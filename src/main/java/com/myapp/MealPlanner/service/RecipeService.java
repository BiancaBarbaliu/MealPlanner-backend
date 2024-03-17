package com.myapp.MealPlanner.service;

import com.myapp.MealPlanner.entity.RecipeEntity;
import com.myapp.MealPlanner.repository.RecipeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RecipeService {
    private final RecipeRepository recipeRepository;
    public RecipeService(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }

    public Page<RecipeEntity> getRecipesByKeyword(String keyword, Pageable pageable) {
        return recipeRepository.findByTagsContainingIgnoreCase(keyword, pageable);
    }

    public Page<RecipeEntity> getAllRecipes(Pageable pageable) {
        return  recipeRepository.findAll(pageable);
    }
}

