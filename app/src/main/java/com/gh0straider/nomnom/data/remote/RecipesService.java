package com.gh0straider.nomnom.data.remote;

import com.gh0straider.nomnom.data.Recipe;

import java.util.List;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by gh0straider on 7/13/17.
 */

public class RecipesService {
  private static final String API_URL =
      "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

  private static Retrofit retrofit;


  public static void init(OkHttpClient client) {
    retrofit = new Retrofit.Builder().baseUrl(API_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
  }

  static ApiCalls createService() {
    ApiCalls service = retrofit.create(ApiCalls.class);
    return service;
  }

  public interface ApiCalls {
    @GET("baking.json") Single<List<Recipe>> getRecipeList();
  }
}
