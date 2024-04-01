package com.myapp.MealPlanner.controller;

import com.myapp.MealPlanner.dto.UserDTO;
import com.myapp.MealPlanner.entity.RecipeEntity;
import com.myapp.MealPlanner.entity.UserEntity;
import com.myapp.MealPlanner.repository.UserRepository;
import com.myapp.MealPlanner.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {
    private final RecipeService recipeService;
    private final UserRepository userRepository;
    public UserProfileController(RecipeService recipeService, UserRepository userRepository){
        this.recipeService = recipeService;
        this.userRepository = userRepository;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));

        UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getEmail());
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/{user_id}/saved-recipes/{recipe_id}")
    public ResponseEntity<?> saveRecipeForUser(@PathVariable Long user_id, @PathVariable Long recipe_id) {
        try {
            recipeService.saveRecipe(user_id, recipe_id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @GetMapping("/{user_id}/saved-recipes")
//    public ResponseEntity<Page<RecipeEntity>> getSavedRecipesForUser(@PathVariable Long user_id, Pageable pageable) {
//        Page<RecipeEntity> savedRecipes = recipeService.getSavedRecipesForUser(user_id, pageable);
//        return ResponseEntity.ok(savedRecipes);
//    }

    @GetMapping("/{userId}/saved-recipes")
    public ResponseEntity<List<RecipeEntity>> getSavedRecipesForUser(@PathVariable Long userId) {
        List<RecipeEntity> savedRecipes = recipeService.getSavedRecipesForUser(userId);
        return ResponseEntity.ok(savedRecipes);
    }


}
