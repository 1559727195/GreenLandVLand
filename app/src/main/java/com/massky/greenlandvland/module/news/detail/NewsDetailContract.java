package com.massky.greenlandvland.module.news.detail;


import com.massky.greenlandvland.base.mvp.IPresenter;
import com.massky.greenlandvland.base.mvp.IView;
import com.massky.greenlandvland.bean.news.NewsMultiArticleDataBean;

/**
 * Created by chenxz on 2018/1/6.
 */

public interface NewsDetailContract {


    interface View extends IView {
        void showWebView(String url, boolean flag);
    }

    interface Presenter extends IPresenter {
        void loadDetailData(NewsMultiArticleDataBean bean);
    }

}
