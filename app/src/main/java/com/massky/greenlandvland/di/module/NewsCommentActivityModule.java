package com.massky.greenlandvland.di.module;


import com.massky.greenlandvland.di.scope.ActivityScope;
import com.massky.greenlandvland.module.news.comment.NewsCommentContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenxz on 2018/1/16.
 */
@Module
public class NewsCommentActivityModule {

    private NewsCommentContract.View view;

    public NewsCommentActivityModule(NewsCommentContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    NewsCommentContract.View provideNewsCommentView(){
        return this.view;
    }

}
