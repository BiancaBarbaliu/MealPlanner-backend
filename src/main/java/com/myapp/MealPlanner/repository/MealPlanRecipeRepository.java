package com.myapp.MealPlanner.repository;

import com.myapp.MealPlanner.entity.MealPlanRecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealPlanRecipeRepository extends JpaRepository<MealPlanRecipeEntity, Long> {
}
