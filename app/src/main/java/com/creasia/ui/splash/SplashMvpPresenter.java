package com.creasia.ui.splash;
import com.creasia.di.PerActivity;
import com.creasia.ui.base.MvpPresenter;
@PerActivity
public interface SplashMvpPresenter<V extends SplashMvpView> extends MvpPresenter<V> {
    void checkLogin();
}
