
package com.creasia.ui.main.route;

import com.creasia.ui.base.MvpPresenter;


public interface RouteMvpPresenter<V extends RouteMvpView>
        extends MvpPresenter<V> {

    void onViewPrepared();
}


