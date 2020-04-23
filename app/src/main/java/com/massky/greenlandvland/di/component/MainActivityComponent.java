package com.massky.greenlandvland.di.component;




import com.massky.greenlandvland.di.module.MainActivityModule;
import com.massky.greenlandvland.di.scope.ActivityScope;
import com.massky.greenlandvland.module.main.MainActivity;

import dagger.Component;

/**
 * Created by chenxz on 2017/12/2.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
