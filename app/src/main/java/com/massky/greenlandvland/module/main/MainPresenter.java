package com.massky.greenlandvland.module.main;


import com.massky.greenlandvland.base.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * Created by chenxz on 2017/12/9.
 */

public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> implements MainContract.Presenter {

    @Inject
    public MainPresenter(MainContract.Model model, MainContract.View view) {
        super(model, view);
    }

}
