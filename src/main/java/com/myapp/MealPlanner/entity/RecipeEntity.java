package com.myapp.MealPlanner.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "recipes")
public class RecipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long recipe_id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "ingredients", columnDefinition = "TEXT")
    private String ingredients;

    @Column(name = "ingredients_raw", columnDefinition = "TEXT")
    private String ingredients_raw;

    @Column(name = "instructions", columnDefinition = "TEXT")
    private String instructions;

    @Column(name = "servings")
    private int servings;

    @Column(name = "serving_size")
    private String serving_size;

    @Column(name= "tags", columnDefinition = "TEXT")
    private String tags;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RecipeEntity.class);

    private static final ObjectMapper mapper = new ObjectMapper();

//    @OneToMany(mappedBy = "recipeEntity")
//    private Set<MealPlanRecipeEntity> mealPlanRecipes;

    public Long getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Long recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getIngredients_raw() {
        return ingredients_raw;
    }

    public void setIngredients_raw(String ingredients_raw) {
        this.ingredients_raw = ingredients_raw;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getServing_size() {
        return serving_size;
    }

    public void setServing_size(String serving_size) {
        this.serving_size = serving_size;
    }

    public String getTags() {
        return tags;
    }

    public List<String> getTagsInList() {
        if (tags == null || tags.trim().isEmpty()) {
            return Collections.emptyList();
        }
        try {
            return mapper.readValue(tags, new TypeReference<List<String>>() {});
        } catch (IOException e) {
            logger.error("Error parsing tags from JSON: '{}'", tags, e);
            return Collections.emptyList();
        }
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "RecipeEntity{" +
                "id=" + recipe_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }

//    public Set<MealPlanRecipeEntity> getMealPlanRecipes() {
//        return mealPlanRecipes;
//    }
//
//    public void setMealPlanRecipes(Set<MealPlanRecipeEntity> mealPlanRecipes) {
//        this.mealPlanRecipes = mealPlanRecipes;
//    }
}
