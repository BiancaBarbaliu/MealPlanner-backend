package com.myapp.MealPlanner.controller;

import com.myapp.MealPlanner.dto.UserDTO;
import com.myapp.MealPlanner.entity.RecipeEntity;
import com.myapp.MealPlanner.entity.UserEntity;
import com.myapp.MealPlanner.repository.UserRepository;
import com.myapp.MealPlanner.service.RecipeService;
import com.myapp.MealPlanner.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {
    private final RecipeService recipeService;
    private final UserRepository userRepository;

    private final UserService userService;
    public UserProfileController(RecipeService recipeService, UserRepository userRepository,
                                 UserService userService){
        this.recipeService = recipeService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));

        UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getDietaryRestriction());
        return ResponseEntity.ok(userDTO);
    }

//    @PostMapping("/{user_id}/saved-recipes/{recipe_id}")
//    public ResponseEntity<?> saveRecipeForUser(@PathVariable Long user_id, @PathVariable Long recipe_id) {
//        try {
//            recipeService.saveRecipe(user_id, recipe_id);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @PostMapping("/{user_id}/saved-recipes/{recipe_id}")
    public ResponseEntity<?> saveRecipeForUser(@PathVariable Long user_id, @PathVariable Long recipe_id) {
        try {
            recipeService.saveRecipe(user_id, recipe_id);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
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

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUserProfile(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        try {
            UserDTO updatedUser = userService.updateUserProfile(userId, userDTO);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{userId}/saved-recipes/{recipeId}")
    public ResponseEntity<Void> deleteSavedRecipe(@PathVariable Long userId, @PathVariable Long recipeId) {
        try {
            recipeService.deleteSavedRecipe(userId, recipeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
