package com.gh0straider.nomnom.RecipeDetails;

import com.gh0straider.nomnom.data.Ingredient;
import com.gh0straider.nomnom.data.Recipe;
import com.gh0straider.nomnom.data.Step;
import com.gh0straider.nomnom.BaseContract;
import java.util.List;

/**
 * Created by gh0straider on6/26/17.
 *
 */

public interface RecipeDetailsContract {
  interface View extends BaseContract.BaseView<Presenter> {
    void showRecipeDetails(Recipe recipe);
    void showIngredients(List<Ingredient> ingredientList);
    void showSteps(List<Step> stepList);
    void showMessage(String message);
  }

  interface Presenter extends BaseContract.BasePresenter<View> {
    void loadRecipeDetails();

    void setRecipeId(int recipeId);
  }
}
