package com.massky.greenlandvland.di.module;


import com.massky.greenlandvland.di.scope.ActivityScope;
import com.massky.greenlandvland.module.news.channel.NewsChannelContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenxz on 2018/1/6.
 */
@Module
public class NewsChannelActivityModule {

    private NewsChannelContract.View view;

    public NewsChannelActivityModule(NewsChannelContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    NewsChannelContract.View provideNewsChannelView() {
        return this.view;
    }

}
