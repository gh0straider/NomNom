package com.gh0straider.nomnom.RecipeList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.gh0straider.nomnom.App;
import com.gh0straider.nomnom.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  private static final String TAG_RECIPE_LIST_FRAGMENT = "TAG_RECIPE_LIST_FRAGMENT";
  @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.main_activity_container) View mContainerView;
  private RecipeListContract.View mRecipeListFragment;
  private RecipeListContract.Presenter presenter;

  @Override public void onBackPressed() {
    if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
      mDrawerLayout.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    int id = item.getItemId();

    switch (id) {
      case R.id.nav_about:
        Snackbar.make(mContainerView, R.string.toast_message,
            Snackbar.LENGTH_LONG).show();
        break;
      case R.id.nav_sync:
        mRecipeListFragment.getPresenter().syncData();
        break;
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    setSupportActionBar(mToolbar);

    ActionBarDrawerToggle toggle =
            new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close);

    mDrawerLayout.addDrawerListener(toggle);
    toggle.syncState();
    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    if (savedInstanceState == null) {
      mRecipeListFragment = RecipeListFragment.newInstance();

      getSupportFragmentManager().
              beginTransaction()
              .replace(R.id.fragmentFrame, (Fragment) mRecipeListFragment, TAG_RECIPE_LIST_FRAGMENT)
              .commit();
    }

    if (mRecipeListFragment == null) {
      mRecipeListFragment = (RecipeListContract.View) getSupportFragmentManager().findFragmentByTag(
              TAG_RECIPE_LIST_FRAGMENT);
    }

    RecipeListContract.Presenter presenter = App.get().presenterProvider.provideRecipeList();
    mRecipeListFragment.setPresenter(presenter);
  }

}
