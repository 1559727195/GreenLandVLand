package com.massky.greenlandvland.di.module;

import com.massky.greenlandvland.di.scope.FragmentScope;
import com.massky.greenlandvland.module.video.main.VideoMainContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenxz on 2018/1/7.
 */
@Module
public class VideoMainFragmentModule {

    private VideoMainContract.View view;

    public VideoMainFragmentModule(VideoMainContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    VideoMainContract.View provideVideoMainView() {
        return this.view;
    }

}
