package com.myapp.MealPlanner.repository;

import com.myapp.MealPlanner.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
    Page<RecipeEntity> findByTagsContainingIgnoreCase(String keyword, Pageable pageable);
}

