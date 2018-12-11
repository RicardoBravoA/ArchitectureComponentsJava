package com.rba.architecturecomponentsjava.app;


import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.rba.architecturecomponentsjava.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class GithubApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initDagger();
        context = getApplicationContext();
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }


    private void initDagger() {
        DaggerAppComponent.builder().application(this).build().inject(this);
    }

}