package com.myapp.MealPlanner.service;

import com.myapp.MealPlanner.Transformer;
import com.myapp.MealPlanner.controller.MealPlanController;
import com.myapp.MealPlanner.dto.MealPlanDTO;
import com.myapp.MealPlanner.embeddable_keys.MealPlanRecipeKey;
import com.myapp.MealPlanner.entity.MealPlanEntity;
import com.myapp.MealPlanner.entity.MealPlanRecipeEntity;
import com.myapp.MealPlanner.entity.RecipeEntity;
import com.myapp.MealPlanner.entity.UserEntity;
import com.myapp.MealPlanner.repository.MealPlanRecipeRepository;
import com.myapp.MealPlanner.repository.MealPlanRepository;
import com.myapp.MealPlanner.repository.RecipeRepository;
import com.myapp.MealPlanner.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MealPlanService {
    private static final Logger logger = LoggerFactory.getLogger(MealPlanController.class);
    private final MealPlanRepository mealPlanRepository;
    private final MealPlanRecipeRepository mealPlanRecipeRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    public MealPlanService(MealPlanRepository mealPlanRepository, MealPlanRecipeRepository mealPlanRecipeRepository,
                           UserRepository userRepository, RecipeRepository recipeRepository){
        this.mealPlanRepository = mealPlanRepository;
        this.mealPlanRecipeRepository = mealPlanRecipeRepository;
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }

    public MealPlanEntity createMealPlan(MealPlanDTO mealPlanDTO){
        logger.info("Received MealPlanDTO: {}", mealPlanDTO);

        UserEntity user = userRepository.getReferenceById(mealPlanDTO.getUserDTO().getId());
        logger.info("UserEntity retrieved: {}", user);

        MealPlanEntity mealPlan = Transformer.toEntity(mealPlanDTO);
        mealPlan.setUserEntity(user);
        logger.info("MealPlanEntity before save: {}", mealPlan);

        final MealPlanEntity savedMealPlan = mealPlanRepository.save(mealPlan);
        logger.info("MealPlanEntity after save: {}", savedMealPlan);


        mealPlanDTO.getRecipeIds().forEach(recipeId -> {
            RecipeEntity recipe = recipeRepository.findById(recipeId)
                    .orElseThrow(() -> new EntityNotFoundException("Recipe not found with id: " + recipeId));

            MealPlanRecipeKey key = new MealPlanRecipeKey();
            key.setMeal_plan_id(savedMealPlan.getMeal_plan_id());
            key.setRecipe_id(recipeId);


            MealPlanRecipeEntity mealPlanRecipe = new MealPlanRecipeEntity();
            mealPlanRecipe.setMealplan_recipe_id(key);
            mealPlanRecipeRepository.save(mealPlanRecipe);
        });

        return savedMealPlan;
    }

//    public List<MealPlanDTO> findMealPlansByUserAndDate(Long userId, Date date) {
//        List<MealPlanEntity> mealPlans = mealPlanRepository.findByUserEntityIdAndDateFetchRecipes(userId, date);
//        return mealPlans.stream()
//                .map(mp -> {
//                    MealPlanDTO dto = Transformer.toDTO(mp);
//                    List<Long> recipeIds = mp.getMealPlanRecipes().stream()
//                            .map(mpr -> mpr.getRecipeEntity().getRecipe_id())
//                            .collect(Collectors.toList());
//                    dto.setRecipeIds(recipeIds);
//                    return dto;
//                })
//                .collect(Collectors.toList());
//    }

    public List<MealPlanDTO> findMealPlansByUserAndDate(Long userId, Date date) {
        List<MealPlanEntity> mealPlans = mealPlanRepository.findByUserEntityIdAndDateFetchRecipes(userId, date);
        if (mealPlans.isEmpty()) {
            logger.info("No meal plans found for user {} on date {}", userId, date);
            return Collections.emptyList();
        }
        return mealPlans.stream()
                .map(Transformer::toDTO)
                .collect(Collectors.toList());
    }



}


