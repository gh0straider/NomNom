package com.gh0straider.nomnom.data.remote;

import com.gh0straider.nomnom.data.Ingredient;
import com.gh0straider.nomnom.data.Recipe;
import com.gh0straider.nomnom.data.Step;
import com.gh0straider.nomnom.data.RecipesDataSource;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by gh0straider 7/13/17.
 */

public class RecipesRemoteDataSource implements RecipesDataSource {

  @Override public Single<List<Recipe>> getRecipes() {
    return RecipesService.createService().getRecipeList();
  }

  @Override public Single<Recipe> getRecipe(int recipeId) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override public Single<List<Ingredient>> getRecipeIngredients(int recipeId) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override public Single<List<Step>> getRecipeSteps(int recipeId) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override public void saveRecipes(List<Recipe> recipeList) {
    throw new UnsupportedOperationException("not implemented");
  }
}
