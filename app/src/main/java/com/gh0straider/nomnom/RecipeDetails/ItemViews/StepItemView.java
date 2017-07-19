package com.gh0straider.nomnom.RecipeDetails.ItemViews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gh0straider.nomnom.R;
import com.gh0straider.nomnom.data.Step;
import com.gh0straider.nomnom.Callbacks;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gh0straider on6/28/17.
 *
 */

@SuppressLint("ViewConstructor") public class StepItemView extends LinearLayout {

  @BindView(R.id.number)TextView number;
  @BindView(R.id.item_name)TextView name;
  @BindView(R.id.video)ImageView videoIcon;

  private Callbacks.StepClicked callback;

  public StepItemView(Context context, Callbacks.StepClicked callback) {
    super(context);
    this.callback = callback;

    inflate(getContext(), R.layout.item_step,this);
    ButterKnife.bind(this,this);

  }

  public View bind(Step step){
    number.setText(String.valueOf(step.getIndex())+".");
    name.setText(step.getShortDescription());
    if(step.getVideoURL()==null || step.getVideoURL().isEmpty())
      videoIcon.setVisibility(INVISIBLE);
    if(callback!=null)setOnClickListener(v -> callback.onClick(step));

    //for testing
    videoIcon.setContentDescription(step.getIndex() + "");
    return this;
  }


}
