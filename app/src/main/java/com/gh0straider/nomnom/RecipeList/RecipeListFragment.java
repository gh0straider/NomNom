package com.gh0straider.nomnom.RecipeList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gh0straider.nomnom.R;
import com.gh0straider.nomnom.ResponsiveUi;
import com.gh0straider.nomnom.data.Recipe;
import com.gh0straider.nomnom.RecipeDetails.RecipeDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipeListFragment extends Fragment implements RecipeListContract.View {

  @BindView(R.id.recycler_home) RecyclerView mRecycler;
  @BindView(R.id.loading_layout_home) ViewGroup mLayoutLoading;
  @BindView(R.id.error_layout_home) ViewGroup mLayoutError;
  @BindView(R.id.error_text) TextView mTextError;

  private Unbinder unbinder;

  private RecipeListContract.Presenter Presenter;

  private RecipeListAdapter mAdapter;

  public RecipeListFragment() {
  }

  public static RecipeListFragment newInstance() {
    return new RecipeListFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);

    unbinder = ButterKnife.bind(this, view);

    return view;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    //RecyclerView
    GridLayoutManager layoutManager =
        new GridLayoutManager(getContext(), ResponsiveUi.getCoulumnNumber());
    mRecycler.setLayoutManager(layoutManager);
    mAdapter = new RecipeListAdapter(
        recipe -> RecipeDetailsActivity.startThisActivity(getContext(), recipe.getId()));
    mRecycler.setAdapter(mAdapter);
  }

  @Override public void onResume() {
    super.onResume();
    Presenter.subscribe(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    Presenter.unsubscribe();
    unbinder.unbind();
  }

  @Override public void showLoading() {
    mLayoutLoading.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    mLayoutLoading.setVisibility(View.GONE);
  }

  @Override public void showError(String error) {
    mLayoutError.setVisibility(View.VISIBLE);
    mTextError.setText(error);
  }

  @Override public void hideError() {
    mLayoutError.setVisibility(View.GONE);
  }

  @Override public void showItems(List<Recipe> recipeList) {
    mAdapter.swapList(recipeList);
  }

  public RecipeListContract.Presenter getPresenter() {
    return Presenter;
  }

  public void setPresenter(RecipeListContract.Presenter Presenter) {
    this.Presenter = Presenter;
  }
}
