package com.gh0straider.nomnom.data;

import com.gh0straider.nomnom.App;
import com.gh0straider.nomnom.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Single;
import okhttp3.HttpUrl;
import okhttp3.Request;
import timber.log.Timber;

/**
 * Created by gh0straider on 7/11/17.
 *
 * Google API Key and Custom SearchEngineID defined in gradle properties file.
 *
 */


public class GoogleImageSearchAPI {

  public static Single<String> getFirstImage(String query) {

    String url = HttpUrl.parse("https://www.googleapis.com/customsearch/v1")
        .newBuilder()
        .addQueryParameter("key", BuildConfig.GoogleApiKey)
        .addQueryParameter("cx", BuildConfig.GoogleSearchEngineId)
        .addQueryParameter("searchType", "image")
        .addQueryParameter("q", query)
        .build()
        .toString();

    Request request = new Request.Builder().url(url).build();

    return Single.fromCallable(() -> App.get().client.newCall(request).execute()).map(response -> {
      String string = getFirstImageFromJson(response.body().string());
      //cache the result because search api free is limited to 100 requests a day
      App.get().preferencesUtils.saveRecipeImage(query,string);
      return string;
    });
  }

  private static String getFirstImageFromJson(String json) {

    try {
      JSONObject root = new JSONObject(json);
      JSONArray items = root.getJSONArray("items");
      return items.getJSONObject(0).getString("link");
    } catch (JSONException e) {
      Timber.e(e, json);
      return null;
    }
  }
}
