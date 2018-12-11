package com.rba.architecturecomponentsjava.di.module;

import com.rba.architecturecomponentsjava.user.UserProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract UserProfileFragment contributeUserProfileFragment();

}
