package com.massky.greenlandvland.di.module;



import com.massky.greenlandvland.di.scope.ActivityScope;
import com.massky.greenlandvland.module.video.detail.VideoDetailContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenxz on 2018/1/11.
 */
@Module
public class VideoDetailActivityModule {

    private VideoDetailContract.View view;

    public VideoDetailActivityModule(VideoDetailContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    VideoDetailContract.View provideVideoDetailView() {
        return this.view;
    }

}
