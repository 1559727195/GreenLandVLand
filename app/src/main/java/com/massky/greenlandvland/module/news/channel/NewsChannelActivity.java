package com.massky.greenlandvland.module.news.channel;

import android.os.Bundle;

import android.view.View;


import com.massky.greenlandvland.R;
import com.massky.greenlandvland.adapter.news.NewsChannelAdapter;
import com.massky.greenlandvland.app.App;
import com.massky.greenlandvland.base.BaseActivity;
import com.massky.greenlandvland.bean.news.NewsChannelBean;
import com.massky.greenlandvland.callbacks.ItemDragHelperCallback;
import com.massky.greenlandvland.di.component.DaggerNewsChannelActivityComponent;
import com.massky.greenlandvland.di.module.NewsChannelActivityModule;

import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class NewsChannelActivity extends BaseActivity<NewsChannelPresenter> implements NewsChannelContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private NewsChannelAdapter mAdapter;

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
        return R.layout.activity_news_channel;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initToolBar(mToolbar, true, getResources().getString(R.string.select_channel));

        List<NewsChannelBean> enableBeans = mPresenter.getChannelBeans(true);
        List<NewsChannelBean> disableBeans = mPresenter.getChannelBeans(false);

        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(manager);

        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);

        mAdapter = new NewsChannelAdapter(this, touchHelper, enableBeans, disableBeans);

        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = mAdapter.getItemViewType(position);
                return viewType == NewsChannelAdapter.TYPE_MY || viewType == NewsChannelAdapter.TYPE_OTHER ? 1 : 4;
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnMyChannelItemClickListener(new NewsChannelAdapter.OnMyChannelItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                // TODO: 2018/1/6
            }
        });
    }

    @Override
    protected void initInject() {
        DaggerNewsChannelActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .newsChannelActivityModule(new NewsChannelActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.saveChannelData(mAdapter.getMyChannelItems(), mAdapter.getOtherChannelItems());
    }

}
