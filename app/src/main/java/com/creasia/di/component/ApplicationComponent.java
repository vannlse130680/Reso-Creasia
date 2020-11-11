package com.creasia.di.component;

import android.app.Application;
import android.content.Context;

import com.creasia.MvpApp;
import com.creasia.data.DataManager;
import com.creasia.di.ApplicationContext;
import com.creasia.di.module.ApplicationModule;
import com.creasia.service.SyncService;
import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MvpApp app);

    void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}