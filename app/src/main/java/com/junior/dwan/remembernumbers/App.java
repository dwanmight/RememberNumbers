package com.junior.dwan.remembernumbers;

import android.app.Activity;
import android.app.Application;

import com.junior.dwan.remembernumbers.di.components.AppComponentV2;
import com.junior.dwan.remembernumbers.di.components.DaggerAppComponentV2;
import com.junior.dwan.remembernumbers.di.modules.ContextModule;
import com.junior.dwan.remembernumbers.di.modules.PreferencesModule;

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

    private static AppComponentV2 mAppComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponentV2.builder()
                .context(new ContextModule(this))
                .preferences(new PreferencesModule())
                .build();
        mAppComponent.inject(this);
//        mAppComponent.inject(this);
//        DaggerAppComponent.builder()
//                .contextModule(new ContextModule(this))
//                .preferencesModule(new PreferencesModule())
//                .build()
//                .inject(this);
    }

    public static AppComponentV2 getAppComponent() {
        return mAppComponent;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mActivityInjector;
    }
}
