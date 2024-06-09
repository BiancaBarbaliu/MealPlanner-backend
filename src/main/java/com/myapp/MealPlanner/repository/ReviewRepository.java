package com.myapp.MealPlanner.repository;

import com.myapp.MealPlanner.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findByRecipeRecipeId(Long recipeId);
    List<ReviewEntity> findByUserId(Long userId);

}
