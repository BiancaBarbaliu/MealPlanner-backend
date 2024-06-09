package com.myapp.MealPlanner.controller;

import com.myapp.MealPlanner.dto.ReviewDTO;
import com.myapp.MealPlanner.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping(value = "/recipe/{recipeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReviewDTO> getReviewsByRecipe(@PathVariable Long recipeId) {
        return reviewService.getReviewsByRecipe(recipeId);
    }

    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<ReviewDTO> getReviewsByUser(@PathVariable Long userId) {
        return reviewService.getReviewsByUser(userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReviewDTO addReview(@RequestBody ReviewDTO reviewDTO) {
        return reviewService.addReview(reviewDTO);
    }
}
