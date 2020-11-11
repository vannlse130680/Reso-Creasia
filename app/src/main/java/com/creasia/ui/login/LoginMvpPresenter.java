package com.creasia.ui.login;


import com.creasia.di.PerActivity;
import com.creasia.ui.base.MvpPresenter;
@PerActivity
public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

    void onServerLoginClick(String email, String password);
}
