package com.massky.greenlandvland.di.module;


import com.massky.greenlandvland.di.scope.FragmentScope;
import com.massky.greenlandvland.module.video.article.VideoArticleContract;
import com.massky.greenlandvland.module.video.article.VideoArticleModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenxz on 2017/12/10.
 */
@Module
public class VideoArticleFragmentModule {

    private VideoArticleContract.View view;

    public VideoArticleFragmentModule(VideoArticleContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    VideoArticleContract.View provideVideoArticleView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    VideoArticleContract.Model provideVideoArticleModel(VideoArticleModel model) {
        return model;
    }


}
