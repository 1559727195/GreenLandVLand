package com.massky.greenlandvland.base.mvp;


import com.massky.greenlandvland.http.RetrofitHelper;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Created by chenxz on 2017/11/30.
 */

public class BaseModel implements IModel, LifecycleObserver {

    protected final String TAG = this.getClass().getSimpleName();

    /**
     * 统一处理 网络请求 和 数据缓存
     */
    protected RetrofitHelper mRetrofitHelper;

    public BaseModel() {
        mRetrofitHelper = RetrofitHelper.getInstance();
    }

    @Override
    public void onDestroy() {
        mRetrofitHelper = null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner owner) {
        owner.getLifecycle().removeObserver(this);
    }

}
