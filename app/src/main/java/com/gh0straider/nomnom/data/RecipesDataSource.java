package com.gh0straider.nomnom.data;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by gh0straider on 7/11/17.
 */

public interface RecipesDataSource {

  Single<List<Recipe>> getRecipes();

  Single<Recipe> getRecipe(int recipeId);

  Single<List<Ingredient>> getRecipeIngredients(int recipeId);

  Single<List<Step>> getRecipeSteps(int recipeId);

  void saveRecipes(List<Recipe> recipeList);

}
