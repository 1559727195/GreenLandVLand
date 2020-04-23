package com.massky.greenlandvland.module.news.comment;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;


import com.massky.greenlandvland.R;
import com.massky.greenlandvland.adapter.news.NewsCommentAdapter;
import com.massky.greenlandvland.app.App;
import com.massky.greenlandvland.base.BaseActivity;
import com.massky.greenlandvland.bean.news.NewsCommentBean;
import com.massky.greenlandvland.di.component.DaggerNewsCommentActivityComponent;
import com.massky.greenlandvland.di.module.NewsCommentActivityModule;
import com.massky.greenlandvland.util.SettingUtil;
import com.massky.greenlandvland.widget.SpaceItemDecoration;
import com.massky.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;

public class NewsCommentActivity extends BaseActivity<NewsCommentPresenter> implements NewsCommentContract.View, XRecyclerView.PullLoadMoreListener {

    private static final String GROUP_ID = "groupId";
    private static final String ITEM_ID = "itemId";
    private String groupId;
    private String itemId;
    private boolean bLoadMore = false;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view)
    XRecyclerView mRecyclerView;

    private NewsCommentAdapter mAdapter;

    public static void launch(String groupId, String itemId) {
        Intent intent = new Intent(App.getContext(), NewsCommentActivity.class)
                .putExtra(GROUP_ID, groupId)
                .putExtra(ITEM_ID, itemId)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.getContext().startActivity(intent);
    }

    @Override
    public void showLoading() {
        mRecyclerView.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mRecyclerView.setPullLoadMoreCompleted();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showErrorMsg(String msg) {
        hideLoading();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_news_comment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Intent intent = getIntent();
        groupId = intent.getStringExtra(GROUP_ID);
        itemId = intent.getStringExtra(ITEM_ID);

        initToolBar(mToolbar, true, "评论");
        mToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.getRecyclerView().smoothScrollToPosition(0);
            }
        });
        mToolbar.setBackgroundColor(SettingUtil.getInstance().getColor());

        mRecyclerView.setLinearLayout();
        mRecyclerView.getRecyclerView().addItemDecoration(new SpaceItemDecoration(this));
        mRecyclerView.setOnPullLoadMoreListener(this);

        List<NewsCommentBean.DataBean.CommentBean> lists = new ArrayList<>();
        mAdapter = new NewsCommentAdapter(this, R.layout.item_news_comment, lists);
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.loadCommentList(groupId, itemId);
    }

    @Override
    protected void initInject() {
        DaggerNewsCommentActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .newsCommentActivityModule(new NewsCommentActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void updateCommentList(List<NewsCommentBean.DataBean.CommentBean> commentBeans) {
        hideLoading();
        if (bLoadMore) {
            mAdapter.appendDatas(commentBeans);
        } else {
            mAdapter.setDatas(commentBeans);
        }
        bLoadMore = false;
    }

    @Override
    public void onRefresh() {
        showLoading();
        mPresenter.loadCommentList(groupId, itemId);
    }

    @Override
    public void onLoadMore() {
        bLoadMore = true;
        mPresenter.loadMoreCommentList();
    }
}
