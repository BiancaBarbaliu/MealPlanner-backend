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
    private Long recipeId;

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

    @Column(name = "tags", columnDefinition = "TEXT")
    private String tags;

    @Column(name = "search_terms", columnDefinition = "TEXT" )
    private String searchTerms;

    @Column(name = "kcal_total")
    private Double kcalTotal;

    @Column(name = "photo_path")
    private String photoPath;


    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RecipeEntity.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipe_id) {
        this.recipeId = recipe_id;
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

    public String getSearchTerms() {
        return searchTerms;
    }

    public void setSearchTerms(String searchTerms) {
        this.searchTerms = searchTerms;
    }

    public Double getKcalTotal() {
        return kcalTotal;
    }

    public void setKcalTotal(Double kcalTotal) {
        this.kcalTotal = kcalTotal;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return "RecipeEntity{" +
                "id=" + recipeId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }
}
