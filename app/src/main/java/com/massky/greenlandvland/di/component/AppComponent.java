package com.massky.greenlandvland.di.component;


import com.massky.greenlandvland.app.App;
import com.massky.greenlandvland.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by chenxz on 2017/12/2.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    App getContext();

}
