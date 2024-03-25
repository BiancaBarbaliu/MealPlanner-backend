package com.myapp.MealPlanner.controller;

import com.myapp.MealPlanner.dto.UserDTO;
import com.myapp.MealPlanner.entity.RecipeEntity;
import com.myapp.MealPlanner.entity.UserEntity;
import com.myapp.MealPlanner.repository.UserRepository;
import com.myapp.MealPlanner.service.RecipeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {
    private final RecipeService recipeService;
    private final UserRepository userRepository;
    public UserProfileController(RecipeService recipeService, UserRepository userRepository){
        this.recipeService = recipeService;
        this.userRepository = userRepository;
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

    @GetMapping("/{user_id}/saved-recipes")
    public ResponseEntity<Page<RecipeEntity>> getSavedRecipesForUser(@PathVariable Long user_id, Pageable pageable) {
        Page<RecipeEntity> savedRecipes = recipeService.getSavedRecipesForUser(user_id, pageable);
        return ResponseEntity.ok(savedRecipes);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        UserDTO userDTO = new UserDTO(user.getId(), user.getEmail(), user.getName());
        return ResponseEntity.ok(userDTO);
    }



}
