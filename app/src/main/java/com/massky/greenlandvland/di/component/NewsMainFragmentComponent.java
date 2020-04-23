package com.massky.greenlandvland.di.component;

import com.massky.greenlandvland.di.module.NewsMainFragmentModule;
import com.massky.greenlandvland.di.scope.FragmentScope;
import com.massky.greenlandvland.module.news.main.NewsMainFragment;

import dagger.Component;

/**
 * Created by chenxz on 2017/12/10.
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = NewsMainFragmentModule.class)
public interface NewsMainFragmentComponent {
    void inject(NewsMainFragment fragment);
}
