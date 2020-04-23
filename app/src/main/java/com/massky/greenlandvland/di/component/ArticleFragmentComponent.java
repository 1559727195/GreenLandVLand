package com.massky.greenlandvland.di.component;


import com.massky.greenlandvland.di.module.ArticleFragmentModule;
import com.massky.greenlandvland.di.scope.FragmentScope;
import com.massky.greenlandvland.module.news.article.ArticleFragment;

import dagger.Component;

/**
 * Created by chenxz on 2017/12/10.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = ArticleFragmentModule.class)
public interface ArticleFragmentComponent {
    void inject(ArticleFragment fragment);
}
