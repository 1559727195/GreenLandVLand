package com.massky.greenlandvland.module.main;



import com.massky.greenlandvland.base.mvp.BaseModel;
import com.massky.greenlandvland.di.scope.ActivityScope;

import javax.inject.Inject;

/**
 * Created by chenxz on 2017/12/9.
 */
@ActivityScope
public class MainModel extends BaseModel implements MainContract.Model {

    @Inject
    public MainModel(){}

}
