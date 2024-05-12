package com.myapp.MealPlanner.embeddable_keys;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
@Embeddable
public class MealPlanRecipeKey implements Serializable {
    @Column(name = "meal_plan_id")
    private Long meal_plan_id;

    @Column(name = "recipe_id")
    private Long recipe_id;

    public Long getMeal_plan_id() {
        return meal_plan_id;
    }

    public void setMeal_plan_id(Long meal_plan_id) {
        this.meal_plan_id = meal_plan_id;
    }

    public Long getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Long recipe_id) {
        this.recipe_id = recipe_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealPlanRecipeKey that = (MealPlanRecipeKey) o;
        return Objects.equals(meal_plan_id, that.meal_plan_id) &&
                Objects.equals(recipe_id, that.recipe_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meal_plan_id, recipe_id);
    }
}
