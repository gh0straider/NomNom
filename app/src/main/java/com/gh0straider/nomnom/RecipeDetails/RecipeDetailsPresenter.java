package com.gh0straider.nomnom.RecipeDetails;

import com.gh0straider.nomnom.data.Recipe;
import com.gh0straider.nomnom.GetRecipeInteractor;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import timber.log.Timber;

/**
 * Created by gh0straider on 6/27/17.
 *
 */

public class RecipeDetailsPresenter implements RecipeDetailsContract.Presenter {

  private CompositeDisposable compositeDisposable = new CompositeDisposable();

  private GetRecipeInteractor interactor;

  private RecipeDetailsContract.View view;
  private int recipeId;

  public RecipeDetailsPresenter() {
    interactor = new GetRecipeInteractor();
  }

  @Override public RecipeDetailsContract.View getView() {
    return view;
  }

  @Override public void subscribe(RecipeDetailsContract.View view) {
    this.view = view;
    loadRecipeDetails();
  }

  @Override public void unsubscribe() {
    compositeDisposable.clear();
  }

  @Override public void loadRecipeDetails() {
    compositeDisposable.add(
        interactor.getRecipeDetails(recipeId).subscribeWith(new DisposableSingleObserver<Recipe>(){

          @Override public void onSuccess(@NonNull Recipe recipe) {
            view.showRecipeDetails(recipe);
            view.showIngredients(recipe.getIngredients());
            view.showSteps(recipe.getSteps());
          }

          @Override public void onError(@NonNull Throwable e) {
            Timber.e(e);
            view.showMessage(e.getMessage());
          }
        })
    );
  }

  @Override public void setRecipeId(int recipeId) {
    this.recipeId = recipeId;
  }
}

