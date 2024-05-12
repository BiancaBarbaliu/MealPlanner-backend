package com.myapp.MealPlanner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myapp.MealPlanner.embeddable_keys.SavedRecipeKey;
import jakarta.persistence.*;

@Entity
@Table(name = "saved_recipes")
public class SavedRecipeEntity {
    @EmbeddedId
    private SavedRecipeKey saved_recipe_id;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity userEntity;

    @ManyToOne
    @MapsId("recipeId")
    @JoinColumn(name = "recipe_id")
    @JsonIgnore
    private RecipeEntity recipeEntity;

    public SavedRecipeKey getSaved_recipe_id() {
        return saved_recipe_id;
    }

    public void setSaved_recipe_id(SavedRecipeKey saved_recipe_id) {
        this.saved_recipe_id = saved_recipe_id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public RecipeEntity getRecipeEntity() {
        return recipeEntity;
    }

    public void setRecipeEntity(RecipeEntity recipeEntity) {
        this.recipeEntity = recipeEntity;
    }
}
