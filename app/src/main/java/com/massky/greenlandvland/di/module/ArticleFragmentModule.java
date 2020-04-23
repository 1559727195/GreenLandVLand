package com.massky.greenlandvland.di.module;



import com.massky.greenlandvland.di.scope.FragmentScope;
import com.massky.greenlandvland.module.news.article.ArticleContract;
import com.massky.greenlandvland.module.news.article.ArticleModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenxz on 2017/12/10.
 */
@Module
public class ArticleFragmentModule {

    private ArticleContract.View view;

    public ArticleFragmentModule(ArticleContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    ArticleContract.View provideChannelView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    ArticleContract.Model provideChannelModel(ArticleModel model) {
        return model;
    }


}
