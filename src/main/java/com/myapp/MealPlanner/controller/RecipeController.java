package com.myapp.MealPlanner.controller;

import com.myapp.MealPlanner.Transformer;
import com.myapp.MealPlanner.dto.RecipeDTO;
import com.myapp.MealPlanner.entity.RecipeEntity;
import com.myapp.MealPlanner.entity.UserEntity;
import com.myapp.MealPlanner.repository.RecipeRepository;
import com.myapp.MealPlanner.repository.UserRepository;
import com.myapp.MealPlanner.service.RecipeRecommendationService;
import com.myapp.MealPlanner.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeRepository recipeRepository;

    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private RecipeRecommendationService recommendationService;

    @Autowired
    private UserRepository userRepository;


    public RecipeController(RecipeService recipeService, RecipeRepository recipeRepository){
        this.recipeService = recipeService;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/breakfast")
    public ResponseEntity<Page<RecipeDTO>> getBreakfastRecipes(Pageable pageable) {
        Page<RecipeEntity> breakfastRecipes = recipeService.getRecipesByKeyword("breakfast", pageable);
        List<RecipeDTO> recipeDTOs = breakfastRecipes.map(Transformer::toDto).getContent();
        return ResponseEntity.ok(new PageImpl<>(recipeDTOs, pageable, breakfastRecipes.getTotalElements()));
    }

    @GetMapping("/lunch")
    public ResponseEntity<Page<RecipeDTO>> getLunchRecipes(Pageable pageable) {
        Page<RecipeEntity> lunchRecipes = recipeService.getRecipesByKeyword("lunch", pageable);
        List<RecipeDTO> recipeDTOs = lunchRecipes.map(Transformer::toDto).getContent();
        return ResponseEntity.ok(new PageImpl<>(recipeDTOs, pageable, lunchRecipes.getTotalElements()));
    }

    @GetMapping("/breakfast/limited")
    public ResponseEntity<List<RecipeDTO>> getLimitedBreakfastRecipes(@RequestParam(name = "limit", defaultValue = "5") int limit) {
        Page<RecipeEntity> breakfastRecipes = recipeService.getRecipesByKeyword("breakfast", PageRequest.of(0, limit));
        List<RecipeDTO> recipeDTOs = breakfastRecipes.getContent().stream().map(Transformer::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(recipeDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable("id") Long id) {
        return recipeRepository.findById(id)
                .map(Transformer::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/lunch/limited")
    public ResponseEntity<List<RecipeDTO>> getLimitedLunchRecipes(@RequestParam(name = "limit", defaultValue = "5") int limit) {
        Page<RecipeEntity> lunchRecipes = recipeService.getRecipesByKeyword("lunch", PageRequest.of(0, limit));
        List<RecipeDTO> recipeDTOs = lunchRecipes.getContent().stream().map(Transformer::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(recipeDTOs);
    }

    @GetMapping("/dinner")
    public ResponseEntity<Page<RecipeDTO>> getDinnerRecipes(Pageable pageable) {
        Page<RecipeEntity> dinnerRecipes = recipeService.getRecipesByKeyword("dinner", pageable);
        List<RecipeDTO> recipeDTOs = dinnerRecipes.map(Transformer::toDto).getContent();
        return ResponseEntity.ok(new PageImpl<>(recipeDTOs, pageable, dinnerRecipes.getTotalElements()));
    }

    @GetMapping("/dinner/limited")
    public ResponseEntity<List<RecipeDTO>> getLimitedDinnerRecipes(@RequestParam(name = "limit", defaultValue = "5") int limit) {
        Page<RecipeEntity> dinnerRecipes = recipeService.getRecipesByKeyword("dinner", PageRequest.of(0, limit));
        List<RecipeDTO> recipeDTOs = dinnerRecipes.getContent().stream().map(Transformer::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(recipeDTOs);
    }

    @GetMapping("/low-carb")
    public ResponseEntity<Page<RecipeDTO>> getLowCarbRecipes(Pageable pageable) {
        Page<RecipeEntity> lowCarbRecipes = recipeService.getRecipesByKeyword("low-carb", pageable);
        List<RecipeDTO> recipeDTOs = lowCarbRecipes.map(Transformer::toDto).getContent();
        return ResponseEntity.ok(new PageImpl<>(recipeDTOs, pageable, lowCarbRecipes.getTotalElements()));
    }

    @GetMapping("/low-carb/limited")
    public ResponseEntity<List<RecipeDTO>> getLimitedLowCarbRecipes(@RequestParam(name = "limit", defaultValue = "5") int limit) {
        Page<RecipeEntity> lowCarbRecipes = recipeService.getRecipesByKeyword("low-carb", PageRequest.of(0, limit));
        List<RecipeDTO> recipeDTOs = lowCarbRecipes.getContent().stream().map(Transformer::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(recipeDTOs);
    }

    @GetMapping("/vegetarian")
    public ResponseEntity<Page<RecipeDTO>> getVegetarianRecipes(Pageable pageable) {
        Page<RecipeEntity> vegetarianRecipes = recipeService.getRecipesByKeyword("vegetarian", pageable);
        List<RecipeDTO> recipeDTOs = vegetarianRecipes.map(Transformer::toDto).getContent();
        return ResponseEntity.ok(new PageImpl<>(recipeDTOs, pageable, vegetarianRecipes.getTotalElements()));
    }

    @GetMapping("/vegetarian/limited")
    public ResponseEntity<List<RecipeDTO>> getLimitedVegetarianRecipes(@RequestParam(name = "limit", defaultValue = "5") int limit) {
        Page<RecipeEntity> vegetarianRecipes = recipeService.getRecipesByKeyword("vegetarian", PageRequest.of(0, limit));
        List<RecipeDTO> recipeDTOs = vegetarianRecipes.getContent().stream().map(Transformer::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(recipeDTOs);
    }

    @GetMapping("/vegan")
    public ResponseEntity<Page<RecipeDTO>> getVeganRecipes(Pageable pageable) {
        Page<RecipeEntity> veganRecipes = recipeService.getRecipesByKeyword("vegan", pageable);
        List<RecipeDTO> recipeDTOs = veganRecipes.map(Transformer::toDto).getContent();
        return ResponseEntity.ok(new PageImpl<>(recipeDTOs, pageable, veganRecipes.getTotalElements()));
    }

    @GetMapping("/vegan/limited")
    public ResponseEntity<List<RecipeDTO>> getLimitedVeganRecipes(@RequestParam(name = "limit", defaultValue = "5") int limit) {
        Page<RecipeEntity> veganRecipes = recipeService.getRecipesByKeyword("vegan", PageRequest.of(0, limit));
        List<RecipeDTO> recipeDTOs = veganRecipes.getContent().stream().map(Transformer::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(recipeDTOs);
    }

    @GetMapping("/salads")
    public ResponseEntity<Page<RecipeDTO>> getSaladsRecipes(Pageable pageable) {
        Page<RecipeEntity> saladsRecipes = recipeService.getRecipesByKeyword("salads", pageable);
        List<RecipeDTO> recipeDTOs = saladsRecipes.map(Transformer::toDto).getContent();
        return ResponseEntity.ok(new PageImpl<>(recipeDTOs, pageable, saladsRecipes.getTotalElements()));
    }

    @GetMapping("/pasta")
    public ResponseEntity<Page<RecipeDTO>> getPastaRecipes(Pageable pageable) {
        Page<RecipeEntity> pastaRecipes = recipeService.getRecipesByKeyword("pasta", pageable);
        List<RecipeDTO> recipeDTOs = pastaRecipes.map(Transformer::toDto).getContent();
        return ResponseEntity.ok(new PageImpl<>(recipeDTOs, pageable, pastaRecipes.getTotalElements()));
    }

    @GetMapping("/snacks")
    public ResponseEntity<Page<RecipeDTO>> getSnacksRecipes(Pageable pageable) {
        Page<RecipeEntity> snacksRecipes = recipeService.getRecipesByKeyword("snacks", pageable);
        List<RecipeDTO> recipeDTOs = snacksRecipes.map(Transformer::toDto).getContent();
        return ResponseEntity.ok(new PageImpl<>(recipeDTOs, pageable, snacksRecipes.getTotalElements()));
    }

    @GetMapping("/desserts")
    public ResponseEntity<Page<RecipeDTO>> getDessertsRecipes(Pageable pageable) {
        Page<RecipeEntity> dessertsRecipes = recipeService.getRecipesByKeyword("desserts", pageable);
        List<RecipeDTO> recipeDTOs = dessertsRecipes.map(Transformer::toDto).getContent();
        return ResponseEntity.ok(new PageImpl<>(recipeDTOs, pageable, dessertsRecipes.getTotalElements()));
    }

    @GetMapping("/all")
    public  ResponseEntity<Page<RecipeDTO>> getAllRecipes(Pageable pageable) {
        Page<RecipeEntity> allRecipes = recipeService.getAllRecipes(pageable);
        List<RecipeDTO> recipeDTOs = allRecipes.map(Transformer::toDto).getContent();
        return ResponseEntity.ok(new PageImpl<>(recipeDTOs, pageable, allRecipes.getTotalElements()));
    }

    @GetMapping(value = "/recommendations/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RecipeDTO>> getRecommendations(@PathVariable Long userId) {

        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        if (userEntity == null) {
            return ResponseEntity.ok(List.of());
        }

        List<RecipeDTO> recommendations = recommendationService.recommendRecipes(userEntity);
        logger.info("Recommendations DTO: {}", recommendations);

        return ResponseEntity.ok(recommendations);
    }

}


