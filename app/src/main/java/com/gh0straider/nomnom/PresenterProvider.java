package com.gh0straider.nomnom;

import com.gh0straider.nomnom.RecipeDetails.RecipeDetailsContract;
import com.gh0straider.nomnom.RecipeDetails.RecipeDetailsPresenter;
import com.gh0straider.nomnom.RecipeList.RecipeListContract;
import com.gh0straider.nomnom.RecipeList.RecipeListPresenter;
import com.gh0straider.nomnom.Steps.StepsContract;
import com.gh0straider.nomnom.Steps.StepsPresenter;

/**
 * Created by gh0straider on 7/1/17.
 *
 */

//This is a temp solution until I learn how to use dagger
public class PresenterProvider {
  public RecipeListContract.Presenter provideRecipeList() {
    return new RecipeListPresenter();
  }

  public RecipeDetailsContract.Presenter provideRecipeDetails() {
    return new RecipeDetailsPresenter();
  }

  public StepsContract.Presenter provideSteps() {
    return new StepsPresenter();
  }
}
