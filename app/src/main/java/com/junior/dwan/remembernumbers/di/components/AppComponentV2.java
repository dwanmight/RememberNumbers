package com.junior.dwan.remembernumbers.di.components;

import android.app.Activity;

import com.junior.dwan.remembernumbers.App;
import com.junior.dwan.remembernumbers.data.managers.PreferencesManager;
import com.junior.dwan.remembernumbers.di.annotations.AppScope;
import com.junior.dwan.remembernumbers.di.modules.ActivityModule;
import com.junior.dwan.remembernumbers.di.modules.ContextModule;
import com.junior.dwan.remembernumbers.di.modules.PreferencesModule;

import dagger.BindsInstance;
import dagger.Component;

@AppScope
@Component(modules = {PreferencesModule.class, ActivityModule.class, ContextModule.class})
public interface AppComponentV2 {

    PreferencesManager preferencesManager();

    @Component.Builder
    interface Builder {
        AppComponentV2 build();

        Builder context(ContextModule context);

        Builder preferences(PreferencesModule preferences);
    }

    void inject(Activity activity);

    void inject(App app);
}
