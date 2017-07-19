package com.gh0straider.nomnom.data;

import com.gh0straider.nomnom.App;
import com.gh0straider.nomnom.data.local.RecipesLocalDataSource;
import com.gh0straider.nomnom.data.remote.RecipesRemoteDataSource;

import java.util.List;

import io.reactivex.Single;
import timber.log.Timber;

/**
 * Created by gh0straider on 7/11/17.
 */

public class RecipeRepository implements RecipesDataSource {

  RecipesLocalDataSource mLDataSource;
  RecipesRemoteDataSource mRDataSource;

  public RecipeRepository(RecipesLocalDataSource mLDataSource,
      RecipesRemoteDataSource mRDataSource) {
    this.mLDataSource = mLDataSource;
    this.mRDataSource = mRDataSource;
  }

  public boolean isDataUpToDate(){
    return App.get().preferencesUtils.getDataUpToDate();
  }

  @Override public Single<List<Recipe>> getRecipes() {

    if(isDataUpToDate()) {
      return mLDataSource.getRecipes();
    }else {
      Timber.d("Data isn't up to data. Retrieving from remote source");
      return mRDataSource.getRecipes();
    }
  }

  @Override public Single<Recipe> getRecipe(int recipeId) {
    return mLDataSource.getRecipe(recipeId);
  }

  @Override public Single<List<Ingredient>> getRecipeIngredients(int recipeId) {
    return mLDataSource.getRecipeIngredients(recipeId);
  }

  @Override public Single<List<Step>> getRecipeSteps(int recipeId) {
    return mLDataSource.getRecipeSteps(recipeId);
  }

  @Override public void saveRecipes(List<Recipe> recipeList) {
    mLDataSource.saveRecipes(recipeList);
  }
}
