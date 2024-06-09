package com.myapp.MealPlanner.service;

import com.myapp.MealPlanner.Transformer;
import com.myapp.MealPlanner.dto.RecipeDTO;
import com.myapp.MealPlanner.entity.RecipeEntity;
import com.myapp.MealPlanner.entity.UserEntity;
import com.myapp.MealPlanner.repository.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecipeRecommendationService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeService recipeService;

    private static final Logger logger = LoggerFactory.getLogger(RecipeRecommendationService.class);

    public List<RecipeDTO> recommendRecipes(UserEntity userEntity) {
        String userDietaryRestriction = userEntity.getDietaryRestriction();
        logger.info("User dietary restriction: {}", userDietaryRestriction);

        List<String> favoriteTags = getFavoriteRecipeTags(userEntity.getId());
        logger.info("Favorite tags: {}", favoriteTags);

        Pageable limit = PageRequest.of(0, 50);

        List<RecipeEntity> allRecipes = recipeRepository.findAll(limit).getContent();


        List<RecipeDTO> recommendedRecipes = allRecipes.stream()
                .peek(recipe -> {
                    List<String> tags = recipe.getTagsInList();
//                    logger.info("Evaluating recipe ID {}: Tags - {}", recipe.getRecipe_id(), tags);
                })
                .filter(recipe -> {
                    boolean matches = matchesDietaryRestrictions(recipe.getTagsInList(), userDietaryRestriction);
//                    logger.info("Recipe ID {} matches dietary restrictions: {}", recipe.getRecipe_id(), matches);
                    return matches;
                })
                .filter(recipe -> {
                    boolean hasSimilarTags = hasSimilarTags(recipe.getTagsInList(), favoriteTags);
//                    logger.info("Recipe ID {} has similar tags: {}", recipe.getRecipe_id(), hasSimilarTags);
                    return hasSimilarTags;
                })
                .map(recipe -> {
                    RecipeDTO dto = Transformer.toDto(recipe);
                    logger.info("Transformed recipe ID {} to DTO.", recipe.getRecipeId());
                    return dto;
                })
                .collect(Collectors.toList());

        logger.info("Number of Recipes recommended: {}", recommendedRecipes.size());
        return recommendedRecipes;
    }

    private boolean matchesDietaryRestrictions(List<String> tags, String restriction) {
        boolean match = tags.stream().anyMatch(tag -> tag.equalsIgnoreCase(restriction));
//        logger.info("Checking dietary restrictions against tags. Restriction: '{}', Tags: '{}', Match Found: {}", restriction, tags, match);
        return match;
    }
    private boolean hasSimilarTags(List<String> recipeTags, List<String> favoriteTags) {
        if (favoriteTags.isEmpty()) {
            logger.info("No favorite tags available.");
            return false;
        }

        Set<String> favoriteTagsSet = new HashSet<>(favoriteTags);
        long commonTagsCount = recipeTags.stream().filter(favoriteTagsSet::contains).count();
        double similarityPercentage = (double) commonTagsCount / favoriteTags.size() * 100;

//        logger.info("Checking similar tags. Common Tags Count: {}, Similarity Percentage: {}", commonTagsCount, similarityPercentage);
        return similarityPercentage >= 30;
    }


    private List<String> getFavoriteRecipeTags(Long userId) {

        List<RecipeEntity> favoriteRecipes = recipeService.getSavedRecipesForUser(userId);

        return favoriteRecipes.stream()
                .flatMap(recipe -> recipe.getTagsInList().stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
