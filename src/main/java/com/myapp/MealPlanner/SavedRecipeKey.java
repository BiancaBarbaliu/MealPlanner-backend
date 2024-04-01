package com.myapp.MealPlanner;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SavedRecipeKey implements Serializable {
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "recipe_id")
    private Long recipe_id;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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
        SavedRecipeKey that = (SavedRecipeKey) o;
        return Objects.equals(user_id, that.user_id) &&
                Objects.equals(recipe_id, that.recipe_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, recipe_id);
    }
}
