package com.gh0straider.nomnom;

/**
 * Created by gh0straider on 7/12/17.
 *
 */

public interface BaseContract {
  interface BaseView<P> {
    void setPresenter(P presenter);
  }

  interface BasePresenter<V> {
    V getView();

    void subscribe(V view);
    void unsubscribe();
  }
}
