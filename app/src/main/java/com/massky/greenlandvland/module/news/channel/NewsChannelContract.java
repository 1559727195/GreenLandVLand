package com.massky.greenlandvland.module.news.channel;



import com.massky.greenlandvland.base.mvp.IPresenter;
import com.massky.greenlandvland.base.mvp.IView;
import com.massky.greenlandvland.bean.news.NewsChannelBean;

import java.util.List;

/**
 * Created by chenxz on 2018/1/6.
 */

public interface NewsChannelContract {

    interface View extends IView {

    }

    interface Presenter extends IPresenter {
        List<NewsChannelBean> getChannelBeans(boolean isEnable);

        void saveChannelData(List<NewsChannelBean> myBeans, List<NewsChannelBean> otherBeans);
    }

}
