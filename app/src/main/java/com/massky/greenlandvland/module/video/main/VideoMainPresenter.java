package com.massky.greenlandvland.module.video.main;



import com.massky.greenlandvland.base.mvp.BasePresenter;
import com.massky.greenlandvland.base.mvp.IModel;

import javax.inject.Inject;

/**
 * Created by chenxz on 2018/1/7.
 */

public class VideoMainPresenter extends BasePresenter<IModel, VideoMainContract.View> implements VideoMainContract.Presenter {

    @Inject
    public VideoMainPresenter(VideoMainContract.View view) {
        super(view);
    }

}
