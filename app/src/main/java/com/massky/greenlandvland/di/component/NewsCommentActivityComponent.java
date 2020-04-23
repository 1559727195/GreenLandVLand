package com.massky.greenlandvland.di.component;



import com.massky.greenlandvland.di.module.NewsCommentActivityModule;
import com.massky.greenlandvland.di.scope.ActivityScope;
import com.massky.greenlandvland.module.news.comment.NewsCommentActivity;

import dagger.Component;

/**
 * Created by chenxz on 2018/1/16.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = NewsCommentActivityModule.class)
public interface NewsCommentActivityComponent {
    void inject(NewsCommentActivity activity);
}
