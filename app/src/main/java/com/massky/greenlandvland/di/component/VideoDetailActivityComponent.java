package com.massky.greenlandvland.di.component;


import com.massky.greenlandvland.di.module.VideoDetailActivityModule;
import com.massky.greenlandvland.di.scope.ActivityScope;
import com.massky.greenlandvland.module.video.detail.VideoDetailActivity;

import dagger.Component;

/**
 * Created by chenxz on 2018/1/15.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = VideoDetailActivityModule.class)
public interface VideoDetailActivityComponent {
    void inject(VideoDetailActivity activity);
}
