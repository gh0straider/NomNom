package com.gh0straider.nomnom.Steps;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.gh0straider.nomnom.data.Step;
import com.gh0straider.nomnom.BaseContract;

/**
 * Created by gh0straider on6/26/17.
 *
 */

public interface StepsContract {

  interface View extends BaseContract.BaseView<Presenter> {
    void showStep(Step step,SimpleExoPlayer player);
    void setNextVisibility(boolean isVisible);
    void setBackVisibility(boolean isVisible);
    void setStepNum(String stepNum);
    void showMessage(String message);
  }

  interface Presenter extends BaseContract.BasePresenter<View> {
    int getCurrentInedex();

    void setCurrentIndex(int index);

    void setRecipeId(int recipeId);
    void showStep(Step step);
    void loadRecipeDetails();
    void showNextStep();
    void showPreviousStep();
  }
}
