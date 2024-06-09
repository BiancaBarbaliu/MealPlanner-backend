package com.myapp.MealPlanner.service;

import com.myapp.MealPlanner.Transformer;
import com.myapp.MealPlanner.dto.ReviewDTO;
import com.myapp.MealPlanner.entity.ReviewEntity;
import com.myapp.MealPlanner.repository.RecipeRepository;
import com.myapp.MealPlanner.repository.ReviewRepository;
import com.myapp.MealPlanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    UserRepository userRepository;

    public List<ReviewDTO> getReviewsByRecipe(Long recipeId) {
        return reviewRepository.findByRecipeRecipeId(recipeId).stream()
                .map(Transformer::toDTO)
                .collect(Collectors.toList());
    }

    public List<ReviewDTO> getReviewsByUser(Long userId) {
        return reviewRepository.findByUserId(userId).stream()
                .map(Transformer::toDTO)
                .collect(Collectors.toList());
    }

    public ReviewDTO addReview(ReviewDTO reviewDTO) {
        ReviewEntity reviewEntity = Transformer.toEntity(reviewDTO, recipeRepository, userRepository);
        ReviewEntity savedReview = reviewRepository.save(reviewEntity);
        return Transformer.toDTO(savedReview);
    }

}
