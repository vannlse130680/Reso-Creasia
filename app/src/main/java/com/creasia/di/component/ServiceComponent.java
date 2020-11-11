package com.creasia.di.component;

import com.creasia.di.PerService;
import com.creasia.di.module.ServiceModule;
import com.creasia.service.SyncService;

import dagger.Component;

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(SyncService service);

}
