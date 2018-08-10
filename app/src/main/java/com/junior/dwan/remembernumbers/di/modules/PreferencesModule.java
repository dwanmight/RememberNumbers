package com.junior.dwan.remembernumbers.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.junior.dwan.remembernumbers.data.managers.PreferencesManager;
import com.junior.dwan.remembernumbers.di.annotations.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PreferencesModule {

    @AppScope
    @Provides
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @AppScope
    @Provides
    PreferencesManager providePreferencesManager(SharedPreferences preferences) {
        return new PreferencesManager(preferences);
    }
}
