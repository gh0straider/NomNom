package com.gh0straider.nomnom.RecipeList;

import com.gh0straider.nomnom.data.Recipe;
import com.gh0straider.nomnom.BaseContract;
import java.util.List;

/**
 * Created by gh0straider on 7/12/17.
 *
 */

public interface RecipeListContract  {

  interface View extends BaseContract.BaseView<Presenter> {

    void showLoading();
    void hideLoading();

    void showError(String error);
    void hideError();

    void showItems(List<Recipe> recipeList);

    Presenter getPresenter();
  }

  interface Presenter extends BaseContract.BasePresenter<View> {
    void getRecipeList();
    void syncData();
  }

}
