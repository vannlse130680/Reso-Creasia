package com.creasia.di.module;

import android.content.Context;

import com.creasia.data.db.model.Task;
import com.creasia.data.network.model.BlogResponse;
import com.creasia.data.network.model.OpenSourceResponse;
import com.creasia.data.network.model.TaskResponse;
import com.creasia.di.ActivityContext;
import com.creasia.di.PerActivity;
import com.creasia.ui.about.AboutMvpPresenter;
import com.creasia.ui.about.AboutMvpView;
import com.creasia.ui.about.AboutPresenter;
import com.creasia.ui.login.LoginMvpPresenter;
import com.creasia.ui.login.LoginMvpView;
import com.creasia.ui.login.LoginPresenter;
import com.creasia.ui.main.RoutePlanPagerAdapter;
import com.creasia.ui.main.MainMvpPresenter;
import com.creasia.ui.main.MainMvpView;
import com.creasia.ui.main.MainPresenter;
import com.creasia.ui.main.TaskAdapter;
import com.creasia.ui.main.route.RouteAdapter;
import com.creasia.ui.main.route.RouteMvpPresenter;
import com.creasia.ui.main.route.RouteMvpView;
import com.creasia.ui.main.route.RoutePresenter;
import com.creasia.ui.main.routeMonth.RouteMonthAdapter;
import com.creasia.ui.main.routeMonth.RouteMonthMvpPresenter;
import com.creasia.ui.main.routeMonth.RouteMonthMvpView;
import com.creasia.ui.main.routeMonth.RouteMonthPresenter;
import com.creasia.ui.main.rating.RatingDialogMvpPresenter;
import com.creasia.ui.main.rating.RatingDialogMvpView;
import com.creasia.ui.main.rating.RatingDialogPresenter;
import com.creasia.ui.task.TaskMvpPresenter;
import com.creasia.ui.task.TaskMvpView;
import com.creasia.ui.task.TaskPresenter;
import com.creasia.ui.task.info.InfoMvpPresenter;
import com.creasia.ui.task.info.InfoMvpView;
import com.creasia.ui.task.info.InfoPresenter;
import com.creasia.ui.task.photo.PhotoMvpPresenter;
import com.creasia.ui.task.photo.PhotoMvpView;
import com.creasia.ui.task.photo.PhotoPresenter;
import com.creasia.ui.splash.SplashMvpPresenter;
import com.creasia.ui.splash.SplashMvpView;
import com.creasia.ui.splash.SplashPresenter;
import com.creasia.utils.rx.AppSchedulerProvider;
import com.creasia.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;



@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    AboutMvpPresenter<AboutMvpView> provideAboutPresenter(
            AboutPresenter<AboutMvpView> presenter) {
        return presenter;
    }

    @Provides
    InfoMvpPresenter<InfoMvpView> provideInfoPresenter(
            InfoPresenter<InfoMvpView> presenter) {
        return presenter;
    }

    @Provides
    PhotoMvpPresenter<PhotoMvpView> providePhotoPresenter(
            PhotoPresenter<PhotoMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(
            LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    TaskMvpPresenter<TaskMvpView> provideRouteDetailPresenter(
            TaskPresenter<TaskMvpView> presenter) {
        return presenter;
    }


    @Provides
    RatingDialogMvpPresenter<RatingDialogMvpView> provideRateUsPresenter(
            RatingDialogPresenter<RatingDialogMvpView> presenter) {
        return presenter;
    }

    @Provides
    RouteMonthMvpPresenter<RouteMonthMvpView> provideOpenSourcePresenter(
            RouteMonthPresenter<RouteMonthMvpView> presenter) {
        return presenter;
    }

    @Provides
    RouteMvpPresenter<RouteMvpView> provideBlogMvpPresenter(
            RoutePresenter<RouteMvpView> presenter) {
        return presenter;
    }

    @Provides
    RoutePlanPagerAdapter provideFeedPagerAdapter(AppCompatActivity activity) {
        return new RoutePlanPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    RouteMonthAdapter provideOpenSourceAdapter() {
        return new RouteMonthAdapter(new ArrayList<OpenSourceResponse.Repo>());
    }

    @Provides
    RouteAdapter provideBlogAdapter() {
        return new RouteAdapter(new ArrayList<BlogResponse.Blog>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    TaskAdapter provideTaskAdapter() {
        return new TaskAdapter(new ArrayList<TaskResponse>());
    }
}
