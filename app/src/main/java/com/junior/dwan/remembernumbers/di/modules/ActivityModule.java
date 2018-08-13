package com.junior.dwan.remembernumbers.di.modules;

import com.junior.dwan.remembernumbers.ui.windows.game.GameActivity;
import com.junior.dwan.remembernumbers.ui.windows.finish.GameFinishActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract GameActivity contributeGameActivityInjector();

    @ContributesAndroidInjector
    abstract GameFinishActivity contributeGameFinishActivityInjector();
}
