package com.myapp.MealPlanner.repository;

import com.myapp.MealPlanner.embeddable_keys.SavedRecipeKey;
import com.myapp.MealPlanner.entity.SavedRecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedRecipeRepository extends JpaRepository<SavedRecipeEntity, SavedRecipeKey> {
    List<SavedRecipeEntity> findAllByUserEntityId(Long user_id);

}
