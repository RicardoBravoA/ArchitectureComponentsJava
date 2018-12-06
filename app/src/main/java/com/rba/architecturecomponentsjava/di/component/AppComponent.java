package com.rba.architecturecomponentsjava.di.component;

import android.app.Application;

import com.rba.architecturecomponentsjava.app.GithubApplication;
import com.rba.architecturecomponentsjava.di.module.ActivityModule;
import com.rba.architecturecomponentsjava.di.module.AppModule;
import com.rba.architecturecomponentsjava.di.module.FragmentModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ActivityModule.class,
        FragmentModule.class, AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(GithubApplication app);
}
