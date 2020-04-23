package com.massky.greenlandvland.module.news.main;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.google.android.material.tabs.TabLayout;
import com.massky.greenlandvland.R;
import com.massky.greenlandvland.adapter.base.BasePagerAdapter;
import com.massky.greenlandvland.app.App;
import com.massky.greenlandvland.base.BaseFragment;
import com.massky.greenlandvland.bean.news.NewsChannelBean;
import com.massky.greenlandvland.di.component.DaggerNewsMainFragmentComponent;
import com.massky.greenlandvland.di.module.NewsMainFragmentModule;
import com.massky.greenlandvland.module.news.article.ArticleFragment;
import com.massky.greenlandvland.module.news.channel.NewsChannelActivity;
import com.massky.greenlandvland.util.SettingUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * Created by chenxz on 2017/11/25.
 */

public class NewsMainFragment extends BaseFragment<NewsMainPresenter> implements NewsMainContract.View {

    @BindView(R.id.tab_layout_news)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager_news)
    ViewPager mViewPager;
    @BindView(R.id.add_channel_iv)
    ImageView iv_add_channel;
    @BindView(R.id.header_layout)
    LinearLayout mLinearLayout;

    private List<Fragment> mFragmentLists;
    private List<String> mTitleLists;
    private BasePagerAdapter mAdapter;
    // 用来存放 Fragment，以便下次直接取
    private HashMap<String, Fragment> mHashMap = new HashMap<>();

    private static NewsMainFragment instance;

    public static NewsMainFragment newInstance() {
        if (instance == null) {
            instance = new NewsMainFragment();
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
        return R.layout.fragment_news_main;
    }

    @Override
    protected void initInject() {
        DaggerNewsMainFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .newsMainFragmentModule(new NewsMainFragmentModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        iv_add_channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/11/25 增加channel
                startActivity(new Intent(mContext, NewsChannelActivity.class));
            }
        });
        mLinearLayout.setBackgroundColor(SettingUtil.getInstance().getColor());

        initTabs();
        mAdapter = new BasePagerAdapter(getChildFragmentManager(), mFragmentLists, mTitleLists);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(mFragmentLists.size());
    }

    @Override
    protected void lazyLoad() {

    }

    private void initTabs() {
        mFragmentLists = new ArrayList<>();
        mTitleLists = new ArrayList<>();
        List<NewsChannelBean> beans = mPresenter.getChannelBeans(true);
        if (beans.size() == 0) {
            mPresenter.initChannelData();
            beans = mPresenter.getChannelBeans(true);
        }
        for (NewsChannelBean bean : beans) {
            Fragment fragment = null;
            String channelId = bean.getChannelId();
            if (mHashMap.containsKey(channelId)) {
                fragment = mHashMap.get(channelId);
            } else {
                fragment = ArticleFragment.newInstance(channelId);
                mHashMap.put(channelId, fragment);
            }
            mFragmentLists.add(fragment);
            mTitleLists.add(bean.getChannelName());
        }
    }

    @Override
    public void updateChannel() {
        initTabs();
        mAdapter.recreateItems(mFragmentLists, mTitleLists);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (instance != null) {
            instance = null;
        }
    }
}
