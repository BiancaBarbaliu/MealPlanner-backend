package com.myapp.MealPlanner.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "dietary_restrictions")
    private String dietary_restrictions;

    @OneToMany(mappedBy = "userEntity")
    @JsonManagedReference
    private Set<SavedRecipeEntity> savedRecipes;


    public Long getId() {
        return id;
    }

    public void setId(Long user_id) {
        this.id = user_id;
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

    public Set<SavedRecipeEntity> getSavedRecipes() {
        return savedRecipes;
    }

    public void setSavedRecipes(Set<SavedRecipeEntity> savedRecipes) {
        this.savedRecipes = savedRecipes;
    }
}
