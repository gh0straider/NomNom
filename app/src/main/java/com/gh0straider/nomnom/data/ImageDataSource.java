package com.gh0straider.nomnom.data;

import io.reactivex.Single;

/**
 * Created by gh0straider on 7/11/17.
 */

public interface ImageDataSource {
  Single<String> getImage(String recipeName);
}
