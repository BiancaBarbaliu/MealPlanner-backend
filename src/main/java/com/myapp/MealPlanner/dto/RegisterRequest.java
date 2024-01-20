package com.myapp.MealPlanner.dto;

public class RegisterRequest {
    private Long user_id;
    private String name;
    private String email;
    private String password;
    private String dietary_restrictions;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDietary_restrictions() {
        return dietary_restrictions;
    }

    public void setDietary_restrictions(String dietary_restrictions) {
        this.dietary_restrictions = dietary_restrictions;
    }
}
