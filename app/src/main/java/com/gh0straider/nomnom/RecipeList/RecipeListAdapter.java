package com.gh0straider.nomnom.RecipeList;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gh0straider.nomnom.R;
import com.gh0straider.nomnom.data.Recipe;
import com.gh0straider.nomnom.Callbacks;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gh0straider on 7/12/17.
 */

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.VH> {

  private List<Recipe> recipeList = new ArrayList<>();
  private Callbacks.AdapterRecipeClicked clickCallback;

  public RecipeListAdapter(Callbacks.AdapterRecipeClicked clickCallback) {
    this.clickCallback = clickCallback;
  }

  @Override public VH onCreateViewHolder(ViewGroup parent, int viewType) {

    return new VH(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false));
  }

  @Override public void onBindViewHolder(VH holder, int position) {
    Recipe mRecipe = recipeList.get(position);
    holder.bind(mRecipe);
    holder.itemView.setOnClickListener(v -> clickCallback.onClick(mRecipe));
  }

  @Override public int getItemCount() {
    return recipeList.size();
  }

  public void swapList(List<Recipe> newRecipeList) {
    recipeList = newRecipeList;
    notifyDataSetChanged();
  }

  public static class VH extends RecyclerView.ViewHolder {

    @BindView(R.id.recipeItemImageView) ImageView imageView;
    @BindView(R.id.recipeItemTextView) TextView textView;
    @BindView(R.id.ingredientCountTextView) TextView ingredientsCountText;
    @BindView(R.id.servingCountTextView) TextView servingsCountText;
    @BindView(R.id.checklistTextView) TextView stepsCountText;

    public VH(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void bind(final Recipe recipe) {
      textView.setText(recipe.getName());
      ingredientsCountText.setText(String.valueOf(recipe.getIngredients().size()));
      servingsCountText.setText(String.valueOf(recipe.getServings()));
      stepsCountText.setText(String.valueOf(recipe.getSteps().size()));
      if (TextUtils.isEmpty(recipe.getImage()) == false) {
        Picasso.with(itemView.getContext())
            .load(recipe.getImage())
            .into(imageView);
      }
    }
  }
}
