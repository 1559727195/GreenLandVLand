package com.massky.greenlandvland.di.component;


import com.massky.greenlandvland.di.module.NewsDetailActivityModule;
import com.massky.greenlandvland.di.scope.ActivityScope;
import com.massky.greenlandvland.module.news.detail.NewsDetailActivity;

import dagger.Component;

/**
 * Created by chenxz on 2018/1/6.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = NewsDetailActivityModule.class)
public interface NewsDetailActivityComponent {
    void inject(NewsDetailActivity activity);
}
