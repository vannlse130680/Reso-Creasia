package com.creasia.di.component;

import com.creasia.di.PerActivity;
import com.creasia.di.module.ActivityModule;
import com.creasia.ui.about.AboutFragment;
import com.creasia.ui.login.LoginActivity;
import com.creasia.ui.main.MainActivity;
import com.creasia.ui.main.route.RouteFragment;
import com.creasia.ui.main.routeMonth.RouteMonthFragment;
import com.creasia.ui.main.rating.RateUsDialog;
import com.creasia.ui.task.TaskActivity;
import com.creasia.ui.task.info.InfoFragment;
import com.creasia.ui.task.photo.PhotoFragment;
import com.creasia.ui.splash.SplashActivity;

import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(LoginActivity activity);

    void inject(SplashActivity activity);

    void inject(TaskActivity activity);

    void inject(AboutFragment fragment);

    void inject(InfoFragment fragment);

    void inject(PhotoFragment fragment);

    void inject(RouteMonthFragment fragment);

    void inject(RouteFragment fragment);

    void inject(RateUsDialog dialog);

}
