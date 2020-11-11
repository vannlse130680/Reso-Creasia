package com.creasia.ui.main.routeMonth;

import com.creasia.ui.base.MvpPresenter;


public interface RouteMonthMvpPresenter<V extends RouteMonthMvpView>
        extends MvpPresenter<V> {

    void onViewPrepared();
}
