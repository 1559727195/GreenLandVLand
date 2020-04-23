package com.massky.greenlandvland.base;


import com.massky.greenlandvland.app.App;
import com.massky.greenlandvland.base.mvp.IView;
import com.massky.greenlandvland.util.NetUtil;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

/**
 * Created by chenxz on 2017/12/7.
 */

public abstract class BaseSubscriber<T> extends ResourceSubscriber<T> {

    private IView mView;
    private String mErrorMsg;

    public BaseSubscriber(IView view) {
        this.mView = view;
    }

    protected BaseSubscriber(IView view, String errorMsg) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    @Override
    public void onError(Throwable e) {
        if (mView == null) {
            return;
        }
        if (e instanceof HttpException) {
            switch (((HttpException) e).code()) {
                case 403:
                    mErrorMsg = "没有权限访问此链接！";
                    break;
                case 504:
                    if (!NetUtil.isConnected(App.getInstance())) {
                        mErrorMsg = "没有联网哦！";
                    } else {
                        mErrorMsg = "网络连接超时！";
                    }
                    break;
                default:
                    mErrorMsg = ((HttpException) e).message();
                    break;
            }
        } else if (e instanceof UnknownHostException) {
            mErrorMsg = "不知名主机！";
        } else if (e instanceof SocketTimeoutException) {
            mErrorMsg = "网络连接超时！";
        }
//        else if (e instanceof JsonMappingException) {
//            errorMsg = "未知异常！";
//        }
        else {
            mErrorMsg = e.getMessage();
        }
        mView.showErrorMsg(mErrorMsg);
    }

    @Override
    public void onComplete() {

    }
}
