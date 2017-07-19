package com.gh0straider.nomnom.RecipeList;

import com.gh0straider.nomnom.App;
import com.gh0straider.nomnom.data.Recipe;
import com.gh0straider.nomnom.GetRecipeListInteractor;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import timber.log.Timber;

/**
 * Created by gh0straider on 7/12/17.
 *
 */

public class RecipeListPresenter implements RecipeListContract.Presenter {

  private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

  private GetRecipeListInteractor interactor;

  private RecipeListContract.View mView;

  public RecipeListPresenter() {
    interactor = new GetRecipeListInteractor();
  }

  @Override public RecipeListContract.View getView() {
    return mView;
  }

  @Override public void subscribe(RecipeListContract.View view) {
    Timber.d("Presenter Subscribing");
    this.mView = view;
    getRecipeList();
  }

  @Override public void unsubscribe() {
    Timber.d("Presenter Unsubscribing");
    mCompositeDisposable.clear();
  }

  @Override public void getRecipeList() {
    Timber.d("Getting Recipe List");
    mView.showLoading();
    mCompositeDisposable.add(
        interactor.getRecipeList().subscribeWith(new DisposableSingleObserver<List<Recipe>>() {
          @Override public void onSuccess(@NonNull List<Recipe> recipes) {
            mView.hideLoading();
            mView.showItems(recipes);
          }

          @Override public void onError(@NonNull Throwable e) {
            Timber.e(e);
            mView.hideLoading();
            mView.showError(e.getMessage());
          }
        }));
  }

  @Override public void syncData() {
    App.get().preferencesUtils.setDataUpToDate(false);
    getRecipeList();
  }
}
