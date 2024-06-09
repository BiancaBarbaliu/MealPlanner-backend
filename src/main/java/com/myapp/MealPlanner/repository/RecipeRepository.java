package com.myapp.MealPlanner.repository;

import com.myapp.MealPlanner.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
    Page<RecipeEntity> findByTagsContainingIgnoreCase(String keyword, Pageable pageable);
    List<RecipeEntity> findByTagsIn(Collection<String> tags);

    Page<RecipeEntity> findAll(Pageable pageable);

    List<RecipeEntity> findByKcalTotalLessThanEqualAndSearchTermsContaining(double kcalTotal, String searchTerm);

    Page<RecipeEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}

