package com.gh0straider.nomnom.data.local;

import com.gh0straider.nomnom.App;
import com.gh0straider.nomnom.data.Ingredient;
import com.gh0straider.nomnom.data.Recipe;
import com.gh0straider.nomnom.data.Step;
import com.gh0straider.nomnom.data.RecipesDataSource;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by gh0straider 7/12/17.
 */

public class RecipesLocalDataSource implements RecipesDataSource {

  RecipesDao dao;

  public RecipesLocalDataSource() {
    dao = App.get().database.recipesDao();
  }
  public RecipesLocalDataSource(RecipesDao dao){
    this.dao = dao;
  }

  private Observable<Recipe> getSubdata(Recipe recipeOnly) {
    return Observable.just(recipeOnly).map(recipe -> {
      recipe.setIngredients(dao.getAllIngredients(recipe.getId()));
      recipe.setSteps(dao.getAllSteps(recipe.getId()));
      return recipe;
    });
  }

  @Override public Single<List<Recipe>> getRecipes() {
    //return Single.fromCallable(() -> {
    //
    //  List<Recipe> recipes = dao.getAllRecipes();
    //  for (Recipe recipe : recipes) {
    //    int recipeId = recipe.getIndex();
    //
    //    List<Ingredient> ingredients = dao.getAllIngredients(recipeId);
    //    List<Step> steps = dao.getAllSteps(recipeId);
    //
    //    recipe.setIngredients(ingredients);
    //    recipe.setSteps(steps);
    //  }
    //  return recipes;
    //
    //});

    return Observable.fromCallable(() -> dao.getAllRecipes())
        .flatMap(Observable::fromIterable)
        .flatMap(this::getSubdata)
        .toList();
  }

  @Override public Single<Recipe> getRecipe(int recipeId) {
    return Observable.fromCallable(() -> dao.getRecipe(recipeId))
        .flatMap(this::getSubdata)
        .firstOrError();
  }

  @Override public Single<List<Ingredient>> getRecipeIngredients(int recipeId) {
    return Single.fromCallable(() -> dao.getAllIngredients(recipeId));
  }

  @Override public Single<List<Step>> getRecipeSteps(int recipeId) {
    return Single.fromCallable(() -> dao.getAllSteps(recipeId));
  }

  @Override public void saveRecipes(List<Recipe> recipeList) {

    dao.deleteAll();

    dao.insertRecipes(recipeList);

    for (Recipe recipe : recipeList) {
      for (int i = 0; i < recipe.getIngredients().size(); i++) {
        recipe.getIngredients().get(i).setRecipeId(recipe.getId());
      }
      for (int i = 0; i < recipe.getSteps().size(); i++) {
        recipe.getSteps().get(i).setRecipeId(recipe.getId());
      }

      dao.insertIngredients(recipe.getIngredients());
      dao.insertSteps(recipe.getSteps());
    }
  }
}
