package com.junior.dwan.remembernumbers.ui.windows.finish.di;

import com.junior.dwan.remembernumbers.di.components.AppComponentV2;
import com.junior.dwan.remembernumbers.ui.windows.finish.ui.GameFinishActivity;

import dagger.Component;

@FinishScope
@Component(dependencies = AppComponentV2.class)
public interface FinishComponent {

    void inject(GameFinishActivity activity);
}
