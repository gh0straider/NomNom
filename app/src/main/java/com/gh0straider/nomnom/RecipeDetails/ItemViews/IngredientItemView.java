package com.gh0straider.nomnom.RecipeDetails.ItemViews;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gh0straider.nomnom.R;
import com.gh0straider.nomnom.data.Ingredient;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gh0straider on 7/12/17.
 */

public class IngredientItemView extends LinearLayout {
  @BindView(R.id.quantity)TextView quantity;
  @BindView(R.id.item_name)TextView name;
  @BindView(R.id.unit)TextView unit;

  public IngredientItemView(Context context) {
    super(context);


    View view = inflate(getContext(), R.layout.item_ingredient,this);
    ButterKnife.bind(this,view);

  }

  public View bind(Ingredient ingredient){
    quantity.setText(String.valueOf(ingredient.getQuantity()));
    name.setText(ingredient.getIngredient());
    unit.setText(ingredient.getMeasure());
    return this;
  }

}
