package com.massky.greenlandvland.di.component;



import com.massky.greenlandvland.di.module.NewsChannelActivityModule;
import com.massky.greenlandvland.di.scope.ActivityScope;
import com.massky.greenlandvland.module.news.channel.NewsChannelActivity;

import dagger.Component;

/**
 * Created by chenxz on 2018/1/6.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = NewsChannelActivityModule.class)
public interface NewsChannelActivityComponent {
    void inject(NewsChannelActivity activity);
}
