package com.myapp.MealPlanner.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "meal_plans")
public class MealPlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_plan_id")
    private Long meal_plan_id;

    @Column(name = "date")
    private Date date;

    @Column(name = "meal_type")
    private String meal_type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "mealPlanEntity")
    @JsonManagedReference
    private Set<MealPlanRecipeEntity> mealPlanRecipes;

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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Set<MealPlanRecipeEntity> getMealPlanRecipes() {
        return mealPlanRecipes;
    }

    public void setMealPlanRecipes(Set<MealPlanRecipeEntity> mealPlanRecipes) {
        this.mealPlanRecipes = mealPlanRecipes;
    }

    @Override
    public String toString() {
        return "MealPlanEntity{" +
                "meal_plan_id=" + meal_plan_id +
                ", date=" + date +
                ", meal_type='" + meal_type + '\'' +
                ", userEntity=" + (userEntity != null ? userEntity.getId() : "null") + // Safe reference to user ID
                '}';
    }

}
