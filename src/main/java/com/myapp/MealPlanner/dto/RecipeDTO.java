package com.myapp.MealPlanner.dto;

public class RecipeDTO {
    private Long recipe_id;
    private String name;
    private String description;
    private String ingredients;
    private String ingredients_raw;
    private String instructions;
    private int servings;
    private String serving_size;

    private String searchTerms;

    private Double kcalTotal;

    private String photoPath;

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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "RecipeDTO{" +
                "id=" + recipe_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ingredients='" + ingredients + '\'' +
                '}';
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
}
