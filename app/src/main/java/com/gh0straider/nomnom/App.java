package com.gh0straider.nomnom;

import android.app.Application;
import android.arch.persistence.room.Room;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.squareup.leakcanary.LeakCanary;
import com.gh0straider.nomnom.data.local.DatabaseContract;
import com.gh0straider.nomnom.data.local.RecipesDatabase;
import com.gh0straider.nomnom.data.remote.RecipesService;
import okhttp3.OkHttpClient;
import timber.log.Timber;

/**
 * Created by gh0straider on 7/08/17.
 *
 * JSON URL
 * https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json
 *
 */

public class App extends Application {

  // singleton
  private static App INSTANCE;

  public OkHttpClient client = new OkHttpClient();

  public RecipesDatabase database;

  public PreferencesUtils preferencesUtils;

  public PresenterProvider presenterProvider;

  public static App get() {
    return INSTANCE;
  }

  @Override public void onCreate() {
    super.onCreate();

    if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return;
    }
    LeakCanary.install(this);

    INSTANCE = this;

    Timber.plant(new Timber.DebugTree());
    Stetho.initializeWithDefaults(this);
    client = new OkHttpClient.Builder().addInterceptor(new StethoInterceptor()).build();

    RecipesService.init(client);

    database = Room.databaseBuilder(this,RecipesDatabase.class, DatabaseContract.DATABASE_NAME).build();

    preferencesUtils = new PreferencesUtils(this);

    presenterProvider = new PresenterProvider();
  }

  //For testing
  public void setPresenterProvider(PresenterProvider presenterProvider) {
    this.presenterProvider = presenterProvider;
  }
}
