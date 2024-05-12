package com.myapp.MealPlanner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myapp.MealPlanner.embeddable_keys.MealPlanRecipeKey;
import jakarta.persistence.*;

@Entity
@Table(name = "mealplan_recipes")
public class MealPlanRecipeEntity {
    @EmbeddedId
    private MealPlanRecipeKey mealplan_recipe_id;
    @ManyToOne
    @MapsId("mealPlanId")
    @JoinColumn(name = "meal_plan_id")
    @JsonIgnore
    private MealPlanEntity mealPlanEntity;

    @ManyToOne
    @MapsId("recipeId")
    @JoinColumn(name = "recipe_id")
    @JsonIgnore
    private RecipeEntity recipeEntity;

    public MealPlanRecipeKey getMealplan_recipe_id() {
        return mealplan_recipe_id;
    }

    public void setMealplan_recipe_id(MealPlanRecipeKey mealplan_recipe_id) {
        this.mealplan_recipe_id = mealplan_recipe_id;
    }

    public MealPlanEntity getMealPlanEntity() {
        return mealPlanEntity;
    }

    public void setMealPlanEntity(MealPlanEntity mealPlanEntity) {
        this.mealPlanEntity = mealPlanEntity;
    }

    public RecipeEntity getRecipeEntity() {
        return recipeEntity;
    }

    public void setRecipeEntity(RecipeEntity recipeEntity) {
        this.recipeEntity = recipeEntity;
    }
}
