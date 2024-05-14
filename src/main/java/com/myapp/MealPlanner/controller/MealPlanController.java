package com.myapp.MealPlanner.controller;

import com.myapp.MealPlanner.Transformer;
import com.myapp.MealPlanner.dto.MealPlanDTO;
import com.myapp.MealPlanner.entity.MealPlanEntity;
import com.myapp.MealPlanner.service.MealPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/mealplans")
public class MealPlanController {
    private static final Logger logger = LoggerFactory.getLogger(MealPlanController.class);
    private final MealPlanService mealPlanService;

    @Autowired
    public MealPlanController(MealPlanService mealPlanService) {
        this.mealPlanService = mealPlanService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MealPlanDTO> createMealPlan(@RequestBody MealPlanDTO mealPlanDTO) {
        logger.info("Received MealPlanDTO: {}", mealPlanDTO);
        MealPlanEntity mealPlanEntity = mealPlanService.createMealPlan(mealPlanDTO);
        MealPlanDTO savedMealPlanDTO = Transformer.toDTO(mealPlanEntity);
        return new ResponseEntity<>(savedMealPlanDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/user/{userId}/date/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MealPlanDTO>> getMealPlansByUserAndDate(
            @PathVariable Long userId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        logger.info("Fetching meal plans for user {} on date {}", userId, date);
        try {
            List<MealPlanDTO> mealPlans = mealPlanService.findMealPlansByUserAndDate(userId, date);
            if (mealPlans.isEmpty()) {
                logger.info("No meal plans found for user {} on date {}", userId, date);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            logger.info("Meal plans found: {}", mealPlans);
            return new ResponseEntity<>(mealPlans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
