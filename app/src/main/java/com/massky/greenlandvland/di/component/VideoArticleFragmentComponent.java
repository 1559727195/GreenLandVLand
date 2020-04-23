package com.massky.greenlandvland.di.component;


import com.massky.greenlandvland.di.module.VideoArticleFragmentModule;
import com.massky.greenlandvland.di.scope.FragmentScope;
import com.massky.greenlandvland.module.video.article.VideoArticleFragment;

import dagger.Component;

/**
 * Created by chenxz on 2017/12/10.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = VideoArticleFragmentModule.class)
public interface VideoArticleFragmentComponent {
    void inject(VideoArticleFragment fragment);
}
