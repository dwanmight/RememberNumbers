package com.junior.dwan.remembernumbers.di.modules;

import com.junior.dwan.remembernumbers.ui.activity.GameActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract GameActivity contributeGameActivityInjector();
}
