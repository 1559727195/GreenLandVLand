package com.massky.greenlandvland.module.video.article;



import com.massky.greenlandvland.base.mvp.IModel;
import com.massky.greenlandvland.base.mvp.IPresenter;
import com.massky.greenlandvland.base.mvp.IView;
import com.massky.greenlandvland.bean.news.NewsMultiArticleBean;
import com.massky.greenlandvland.bean.news.NewsMultiArticleDataBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by chenxz on 2018/1/7.
 */

public interface VideoArticleContract {

    interface View extends IView {
        void updateVideoArticleList(List<NewsMultiArticleDataBean> lists);

        void updateMoreVideoArticleList(List<NewsMultiArticleDataBean> lists);
    }

    interface Presenter extends IPresenter {
        void loadVideoArticleList(String category, String minBehotTime, boolean isUpdate);

        void loadMoreVideoArticleList(String category, String maxBehotTime, boolean isUpdate);
    }

    interface Model extends IModel {
        Observable<NewsMultiArticleBean> loadVideoArticleList(String category, String minBehotTime, boolean isUpdate);

        Observable<NewsMultiArticleBean> loadMoreVideoArticleList(String category, String maxBehotTime, boolean isUpdate);
    }


}
