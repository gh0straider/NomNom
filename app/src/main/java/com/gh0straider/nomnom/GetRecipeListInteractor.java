package com.gh0straider.nomnom;

import com.gh0straider.nomnom.data.Recipe;
import com.gh0straider.nomnom.data.ImageRepository;
import com.gh0straider.nomnom.data.RecipeRepository;
import com.gh0straider.nomnom.data.local.RecipesLocalDataSource;
import com.gh0straider.nomnom.data.remote.RecipesRemoteDataSource;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by gh0straider on 7/11/17.
 */

public class GetRecipeListInteractor {
  private RecipeRepository mRecipeRepository;
  private ImageRepository mImageRepository;

  public GetRecipeListInteractor() {
    this.mRecipeRepository =
        new RecipeRepository(new RecipesLocalDataSource(), new RecipesRemoteDataSource());
    this.mImageRepository = new ImageRepository();
  }

  public Single<List<Recipe>> getRecipeList() {

    if(mRecipeRepository.isDataUpToDate()){
      Timber.d("Data is up to date. Retrieving from local source");
      return mRecipeRepository.getRecipes().compose(RxUtils.applySchedulersSingle());
    }

    return mRecipeRepository.getRecipes()
        .flattenAsObservable(recipes -> recipes)
        .flatMap(r -> Observable.just(r).subscribeOn(Schedulers.io()).map(recipe -> {
          if (recipe.getImage() == null || recipe.getImage().isEmpty()) {
            Timber.d("Requesting for '%s' image from the repository", recipe.getName());
            String imgUrl = mImageRepository.getImage(recipe.getName()).blockingGet();
            recipe.setImage(imgUrl);
          }
          return recipe;
        }))
        .toList()
        .doOnSuccess(recipes -> {
          Timber.d("Saving recipes list into database");
          mRecipeRepository.saveRecipes(recipes);
          App.get().preferencesUtils.setDataUpToDate(true);
        })
        .compose(RxUtils.applySchedulersSingle());
  }
}
