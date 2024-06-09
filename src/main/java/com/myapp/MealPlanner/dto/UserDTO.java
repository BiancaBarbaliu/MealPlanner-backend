package com.myapp.MealPlanner.dto;

public class UserDTO {
    private Long id;
    private String name;
    private String email;

    private String dietaryRestriction;

    public String getDietaryRestriction() {
        return dietaryRestriction;
    }

    public void setDietaryRestriction(String dietaryRestriction) {
        this.dietaryRestriction = dietaryRestriction;
    }

    public UserDTO(){

    }

    public UserDTO(Long id, String name, String email, String dietaryRestriction){
        this.id = id;
        this.name = name;
        this.email = email;
        this.dietaryRestriction = dietaryRestriction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "Id=" + id +
                ", name=" + name +
                ", email=" + email +
                '}';
    }

}
