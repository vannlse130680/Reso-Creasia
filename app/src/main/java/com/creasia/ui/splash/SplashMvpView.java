package com.creasia.ui.splash;
import com.creasia.ui.base.MvpView;
public interface SplashMvpView extends MvpView {

    void openLoginActivity();

    void openMainActivity();

    void startSyncService();
}
