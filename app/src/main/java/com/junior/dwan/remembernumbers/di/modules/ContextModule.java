package com.junior.dwan.remembernumbers.di.modules;

import android.content.Context;

import com.junior.dwan.remembernumbers.di.annotations.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @AppScope
    @Provides
    Context provideContext() {
        return context;
    }
}
