package com.massky.greenlandvland.module.news.main;


import com.massky.greenlandvland.base.mvp.IPresenter;
import com.massky.greenlandvland.base.mvp.IView;
import com.massky.greenlandvland.bean.news.NewsChannelBean;

import java.util.List;

/**
 * Created by chenxz on 2017/12/10.
 */

public interface NewsMainContract {

    interface View extends IView {
        void updateChannel();
    }

    interface Presenter extends IPresenter {
        List<NewsChannelBean> getChannelBeans(boolean isEnable);

        void initChannelData();
    }

}
