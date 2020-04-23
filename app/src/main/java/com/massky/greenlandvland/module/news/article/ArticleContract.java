package com.massky.greenlandvland.module.news.article;

import com.massky.greenlandvland.base.mvp.IModel;
import com.massky.greenlandvland.base.mvp.IPresenter;
import com.massky.greenlandvland.base.mvp.IView;
import com.massky.greenlandvland.bean.news.NewsMultiArticleBean;
import com.massky.greenlandvland.bean.news.NewsMultiArticleDataBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by chenxz on 2017/12/10.
 */

public interface ArticleContract {

    interface View extends IView {
        void updateNewsArticleList(List<NewsMultiArticleDataBean> lists);

        void updateMoreNewsArticleList(List<NewsMultiArticleDataBean> lists);
    }

    interface Presenter extends IPresenter {
        void loadNewsArticleList(String category, String minBehotTime, boolean isUpdate);

        void loadMoreNewsArticleList(String category, String maxBehotTime, boolean isUpdate);
    }

    interface Model extends IModel {
        Observable<NewsMultiArticleBean> loadNewsArticleList(String category, String minBehotTime, boolean isUpdate);

        Observable<NewsMultiArticleBean> loadMoreNewsArticleList(String category, String maxBehotTime, boolean isUpdate);
    }

}
