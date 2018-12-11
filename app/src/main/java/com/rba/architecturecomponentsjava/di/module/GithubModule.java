package com.rba.architecturecomponentsjava.di.module;

import com.rba.architecturecomponentsjava.github.GithubActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class GithubModule {
    @ContributesAndroidInjector()
    abstract GithubActivity contributeGithubActivity();
}
