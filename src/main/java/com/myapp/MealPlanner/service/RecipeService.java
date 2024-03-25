package com.myapp.MealPlanner.service;

import com.myapp.MealPlanner.SavedRecipeKey;
import com.myapp.MealPlanner.entity.RecipeEntity;
import com.myapp.MealPlanner.entity.SavedRecipeEntity;
import com.myapp.MealPlanner.repository.RecipeRepository;
import com.myapp.MealPlanner.repository.SavedRecipeRepository;
import com.myapp.MealPlanner.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final SavedRecipeRepository savedRecipeRepository;
    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository, SavedRecipeRepository savedRecipeRepository){
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.savedRecipeRepository = savedRecipeRepository;
    }

    public Page<RecipeEntity> getRecipesByKeyword(String keyword, Pageable pageable) {
        return recipeRepository.findByTagsContainingIgnoreCase(keyword, pageable);
    }

    public Page<RecipeEntity> getAllRecipes(Pageable pageable) {
        return  recipeRepository.findAll(pageable);
    }

    public void saveRecipe(Long user_id, Long recipe_id){
        userRepository.findById(user_id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + user_id));
        recipeRepository.findById(recipe_id)
                .orElseThrow(() -> new EntityNotFoundException("Recipe not found with id: " + recipe_id));

        SavedRecipeKey key = new SavedRecipeKey();
        key.setUser_id(user_id);
        key.setRecipe_id(recipe_id);

        if(savedRecipeRepository.findById(key).isPresent()) {
            throw new IllegalStateException("Recipe already saved by this user");
        }

        SavedRecipeEntity savedRecipe = new SavedRecipeEntity();
        savedRecipe.setSaved_recipe_id(key);

        savedRecipeRepository.save(savedRecipe);

    }

    public Page<RecipeEntity> getSavedRecipesForUser(Long user_id, Pageable pageable) {
        Page<SavedRecipeEntity> savedRecipesPage = savedRecipeRepository.findAllByUserEntityId(user_id, pageable);

        return savedRecipesPage.map(savedRecipe -> {
            return recipeRepository.findById(savedRecipe.getSaved_recipe_id().getRecipe_id())
                    .orElseThrow(() -> new RuntimeException("Recipe not found with id: " + savedRecipe.getSaved_recipe_id().getRecipe_id()));
        });
    }

}

