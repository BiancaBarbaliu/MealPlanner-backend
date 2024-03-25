package com.myapp.MealPlanner.repository;

import com.myapp.MealPlanner.SavedRecipeKey;
import com.myapp.MealPlanner.entity.SavedRecipeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface SavedRecipeRepository extends JpaRepository<SavedRecipeEntity, SavedRecipeKey> {
    Page<SavedRecipeEntity> findAllByUserEntityId(@Param("user_id")Long user_id, Pageable pageable);

}
