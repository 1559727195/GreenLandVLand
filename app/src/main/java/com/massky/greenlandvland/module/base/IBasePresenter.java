package com.massky.greenlandvland.module.base;

/**
 * Created by chenxz on 2017/11/25.
 */
@Deprecated
public interface IBasePresenter {

    /**
     * 刷新数据
     */
    void doRefresh();

    /**
     * 显示网络错误
     */
    void doShowNetError();
}
