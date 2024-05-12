package com.myapp.MealPlanner.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class MealPlanDTO {
    @JsonProperty("id")
    private Long meal_plan_id;

    @JsonProperty("user")
    private UserDTO userDTO;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("mealType")
    private String meal_type;

    @JsonProperty("recipeIds")
    private List<Long> recipeIds;


    public Long getMeal_plan_id() {
        return meal_plan_id;
    }

    public void setMeal_plan_id(Long meal_plan_id) {
        this.meal_plan_id = meal_plan_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMeal_type() {
        return meal_type;
    }

    public void setMeal_type(String meal_type) {
        this.meal_type = meal_type;
    }

    public List<Long> getRecipeIds() {
        return recipeIds;
    }

    public void setRecipeIds(List<Long> recipeIds) {
        this.recipeIds = recipeIds;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public String toString() {
        return "MealPlanDTO{" +
                "mealPlanId=" + meal_plan_id +
                ", userId=" + userDTO +
                ", date=" + date +
                ", mealType='" + meal_type + '\'' +
                ", recipeIds=" + recipeIds +
                '}';
    }
}
