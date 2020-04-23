package com.massky.greenlandvland.di.module;



import com.massky.greenlandvland.di.scope.ActivityScope;
import com.massky.greenlandvland.module.main.MainContract;
import com.massky.greenlandvland.module.main.MainModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenxz on 2017/12/2.
 */
@Module
public class MainActivityModule {

    private MainContract.View view;

    public MainActivityModule(MainContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MainContract.View provideMainView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MainContract.Model provideMainModel(MainModel model) {
        return model;
    }

}
