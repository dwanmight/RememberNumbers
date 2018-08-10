package com.junior.dwan.remembernumbers.di.components;

import com.junior.dwan.remembernumbers.App;
import com.junior.dwan.remembernumbers.di.modules.ActivityModule;
import com.junior.dwan.remembernumbers.di.modules.PreferencesModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {PreferencesModule.class, ActivityModule.class})
public interface AppComponentV2 {

    @Component.Builder
    interface Builder {
        AppComponentV2 build();

        @BindsInstance
        Builder application(App app);
    }
}
