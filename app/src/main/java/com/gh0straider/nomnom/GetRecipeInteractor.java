package com.gh0straider.nomnom;

import com.gh0straider.nomnom.data.Recipe;
import com.gh0straider.nomnom.data.RecipeRepository;
import com.gh0straider.nomnom.data.local.RecipesLocalDataSource;
import com.gh0straider.nomnom.data.remote.RecipesRemoteDataSource;

import io.reactivex.Single;

/**
 * Created by gh0straider on 7/11/17.
 */

public class GetRecipeInteractor {

  private RecipeRepository mRecipeRepository;

  public GetRecipeInteractor() {
    this.mRecipeRepository =
        new RecipeRepository(new RecipesLocalDataSource(), new RecipesRemoteDataSource());
  }

  public Single<Recipe> getRecipeDetails(int recipeId) {
    return mRecipeRepository.getRecipe(recipeId).compose(RxUtils.applySchedulersSingle());
  }
}
