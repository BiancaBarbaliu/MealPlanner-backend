package com.myapp.MealPlanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class MealPlannerApplication {

	public static void main(String[] args) {
		System.out.println("Hello!!!");
		SpringApplication.run(MealPlannerApplication.class, args);
	}

}
