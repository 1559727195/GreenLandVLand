package com.massky.greenlandvland.module.video.main;

import android.os.Bundle;


import com.google.android.material.tabs.TabLayout;
import com.massky.greenlandvland.R;
import com.massky.greenlandvland.adapter.base.BasePagerAdapter;
import com.massky.greenlandvland.app.App;
import com.massky.greenlandvland.base.BaseFragment;
import com.massky.greenlandvland.di.component.DaggerVideoMainFragmentComponent;
import com.massky.greenlandvland.di.module.VideoMainFragmentModule;
import com.massky.greenlandvland.module.video.article.VideoArticleFragment;
import com.massky.greenlandvland.util.SettingUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * Created by chenxz on 2018/1/7.
 */

public class VideoMainFragment extends BaseFragment<VideoMainPresenter> implements VideoMainContract.View {

    @BindView(R.id.tab_layout_video)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager_video)
    ViewPager mViewPager;

    private List<Fragment> mFragmentLists;
    private List<String> mTitleLists;
    private BasePagerAdapter mAdapter;
    // 用来存放 Fragment，以便下次直接取
    private HashMap<String, Fragment> mHashMap = new HashMap<>();

    private static VideoMainFragment instance;

    public static VideoMainFragment newInstance() {
        if (instance == null) {
            instance = new VideoMainFragment();
        }
        return instance;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_video_main;
    }

    @Override
    protected void initInject() {
        DaggerVideoMainFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .videoMainFragmentModule(new VideoMainFragmentModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setBackgroundColor(SettingUtil.getInstance().getColor());
        String categoryId[] = App.getContext().getResources().getStringArray(R.array.tab_video_id);
        String categoryName[] = App.getContext().getResources().getStringArray(R.array.tab_video_name);
        mFragmentLists = new ArrayList<>();
        for (int i = 0; i < categoryId.length; i++) {
            Fragment fragment = VideoArticleFragment.newInstance(categoryId[i]);
            mFragmentLists.add(fragment);
        }
        mAdapter = new BasePagerAdapter(getChildFragmentManager(), mFragmentLists, categoryName);
        mViewPager.setOffscreenPageLimit(categoryId.length);
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (instance != null) {
            instance = null;
        }
    }
}
