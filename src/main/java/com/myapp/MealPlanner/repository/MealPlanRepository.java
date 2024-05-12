package com.myapp.MealPlanner.repository;

import com.myapp.MealPlanner.entity.MealPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MealPlanRepository extends JpaRepository<MealPlanEntity,Long> {
    @Query("SELECT mp FROM MealPlanEntity mp " +
            "LEFT JOIN FETCH mp.mealPlanRecipes mpr " +
            "WHERE mp.userEntity.id = :userId AND mp.date = :date")
    List<MealPlanEntity> findByUserEntityIdAndDateFetchRecipes(@Param("userId") Long userId, @Param("date") Date date);
}
