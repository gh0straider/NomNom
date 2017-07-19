package com.gh0straider.nomnom;

import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gh0straider on 7/10/17.
 */

public class RxUtils {
  public static <T> SingleTransformer<T, T> applySchedulersSingle() {
    return observable -> observable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
  public static <T> ObservableTransformer<T, T> applySchedulersObservable() {
    return observable -> observable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
