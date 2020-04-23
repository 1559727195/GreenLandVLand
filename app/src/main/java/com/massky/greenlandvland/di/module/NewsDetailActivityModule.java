package com.massky.greenlandvland.di.module;

;

import com.massky.greenlandvland.di.scope.ActivityScope;
import com.massky.greenlandvland.module.news.detail.NewsDetailContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenxz on 2018/1/6.
 */

@Module
public class NewsDetailActivityModule {

    private NewsDetailContract.View view;

    public NewsDetailActivityModule(NewsDetailContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    NewsDetailContract.View provideNewsDetailView() {
        return this.view;
    }

}
