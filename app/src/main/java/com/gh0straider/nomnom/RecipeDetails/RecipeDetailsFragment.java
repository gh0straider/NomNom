package com.gh0straider.nomnom.RecipeDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gh0straider.nomnom.Callbacks;
import com.gh0straider.nomnom.R;
import com.gh0straider.nomnom.RecipeDetails.ItemViews.IngredientItemView;
import com.gh0straider.nomnom.RecipeDetails.ItemViews.StepItemView;
import com.gh0straider.nomnom.data.Ingredient;
import com.gh0straider.nomnom.data.Recipe;
import com.gh0straider.nomnom.data.Step;
import com.squareup.picasso.Picasso;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailsFragment extends Fragment implements RecipeDetailsContract.View {

  private static final String KEY_RECIPE_ID = "RECIPE_ID";

  @BindView(R.id.background_image) ImageView recipeImageView;
  @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout toolbarLayout;
  @BindView(R.id.toolbar) Toolbar toolbar;

  @BindView(R.id.ingredient_layout_list) LinearLayout ingredientsListLayout;
  @BindView(R.id.steps_layout_list) LinearLayout stepsListLayout;

  private RecipeDetailsContract.Presenter presenter;
  private int recipeId;

  private Unbinder unbinder;

  private Callbacks.StepClicked callback;

  public RecipeDetailsFragment() {
    // Required empty public constructor
  }

  public static RecipeDetailsFragment newInstance(int recipeId) {
    Bundle args = new Bundle();
    RecipeDetailsFragment fragment = new RecipeDetailsFragment();
    args.putInt(KEY_RECIPE_ID, recipeId);
    fragment.setArguments(args);
    return fragment;
  }

  public void setCallback(Callbacks.StepClicked callback) {
    this.callback = callback;
  }

  @SuppressWarnings("ConstantConditions") @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);
    unbinder = ButterKnife.bind(this, view);
    ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    return view;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    recipeId = getArguments().getInt(KEY_RECIPE_ID);
  }

  @Override public void onResume() {
    super.onResume();
    presenter.subscribe(this);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override public void onPause() {
    super.onPause();
    presenter.unsubscribe();
  }

  @Override public void showRecipeDetails(Recipe recipe) {
    Picasso.with(getContext()).load(recipe.getImage()).into(recipeImageView);
    toolbarLayout.setTitle(recipe.getName());
  }

  @Override public void showIngredients(List<Ingredient> ingredientList) {
    ingredientsListLayout.removeAllViews();
    for (Ingredient ingredient : ingredientList) {
      ingredientsListLayout.addView(new IngredientItemView(getContext()).bind(ingredient));
    }
  }

  @Override public void showSteps(List<Step> stepList) {
    stepsListLayout.removeAllViews();
    for (Step step : stepList) {
      stepsListLayout.addView(new StepItemView(getContext(), callback).bind(step));
    }
  }

  @Override public void showMessage(String message) {
    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
  }

  public void setPresenter(RecipeDetailsContract.Presenter Presenter) {
    this.presenter = Presenter;
  }
}
