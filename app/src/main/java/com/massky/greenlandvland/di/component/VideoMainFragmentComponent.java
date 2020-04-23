package com.massky.greenlandvland.di.component;


import com.massky.greenlandvland.di.module.VideoMainFragmentModule;
import com.massky.greenlandvland.di.scope.FragmentScope;
import com.massky.greenlandvland.module.video.main.VideoMainFragment;

import dagger.Component;

/**
 * Created by chenxz on 2018/1/7.
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = VideoMainFragmentModule.class)
public interface VideoMainFragmentComponent {
    void inject(VideoMainFragment fragment);
}
