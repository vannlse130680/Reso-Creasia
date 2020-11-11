

package com.creasia.ui.main;


import com.creasia.di.PerActivity;
import com.creasia.ui.base.MvpPresenter;



@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void onDrawerOptionAboutClick();

    void onDrawerOptionLogoutClick();

    void onDrawerRateUsClick();

    void onViewInitialized();

    void onAddNewTask();

    void onNavMenuCreated();

    void onViewPrepared();

    void rsPageIndex();

    // increase page index
    void inPageIndex();
    void onAsyncData();

}
