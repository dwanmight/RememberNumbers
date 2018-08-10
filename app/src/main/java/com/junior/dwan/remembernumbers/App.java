package com.junior.dwan.remembernumbers;

import android.app.Activity;
import android.app.Application;

import com.junior.dwan.remembernumbers.di.components.DaggerAppComponentV2;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by Might on 04.11.2016.
 */

public class App extends Application
        implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> mActivityInjector;


    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponentV2.builder()
                .application(this)
                .build();
//        DaggerAppComponent.builder()
//                .contextModule(new ContextModule(this))
//                .preferencesModule(new PreferencesModule())
//                .build()
//                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mActivityInjector;
    }
}
