package com.gh0straider.nomnom;

import com.gh0straider.nomnom.data.Recipe;
import com.gh0straider.nomnom.data.Step;

/**
 * Created by gh0straider on 7/12/17.
 */

public interface Callbacks {
  interface AdapterRecipeClicked{
    void onClick(Recipe recipe);
  }
  interface StepClicked {
    void onClick(Step step);
  }
}
