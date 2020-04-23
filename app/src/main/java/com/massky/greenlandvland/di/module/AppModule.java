package com.massky.greenlandvland.di.module;

import com.massky.greenlandvland.app.App;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenxz on 2017/12/2.
 */

@Module
public class AppModule {

    private App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

}

