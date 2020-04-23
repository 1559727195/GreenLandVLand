package com.massky.greenlandvland.module.video.detail;


import com.massky.greenlandvland.base.mvp.IPresenter;
import com.massky.greenlandvland.base.mvp.IView;
import com.massky.greenlandvland.bean.news.NewsCommentBean;

import java.util.List;

/**
 * Created by chenxz on 2018/1/11.
 */

public interface VideoDetailContract {

    interface View extends IView {
        void updateCommentList(List<NewsCommentBean.DataBean.CommentBean> commentBeans);

        void setVideoUrl(String url);
    }

    interface Presenter extends IPresenter {
        void loadCommentList(String... groupId_itemId);

        void loadMoreCommentList();

        void loadVideoDetailData(String videoId);
    }

}
