package com.rba.architecturecomponentsjava.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.rba.architecturecomponentsjava.base.FactoryViewModel;
import com.rba.architecturecomponentsjava.di.key.ViewModelKey;
import com.rba.architecturecomponentsjava.github.GithubViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class GithubViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GithubViewModel.class)
    abstract ViewModel bindGithubViewModel(GithubViewModel githubViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);

}
