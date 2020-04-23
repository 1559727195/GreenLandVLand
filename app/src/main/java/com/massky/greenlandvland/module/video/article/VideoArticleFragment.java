package com.massky.greenlandvland.module.video.article;

import android.os.Bundle;
import android.widget.LinearLayout;


import com.massky.greenlandvland.R;
import com.massky.greenlandvland.adapter.news.ArticleMultiListAdapter;
import com.massky.greenlandvland.app.App;
import com.massky.greenlandvland.base.BaseFragment;
import com.massky.greenlandvland.bean.news.NewsMultiArticleDataBean;
import com.massky.greenlandvland.di.component.DaggerVideoArticleFragmentComponent;
import com.massky.greenlandvland.di.module.VideoArticleFragmentModule;
import com.massky.greenlandvland.util.TimeUtil;
import com.massky.greenlandvland.widget.SpaceItemDecoration;
import com.massky.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by chenxz on 2018/1/7.
 */

public class VideoArticleFragment extends BaseFragment<VideoArticlePresenter> implements VideoArticleContract.View, XRecyclerView.PullLoadMoreListener {

    private static String CATEGORY_ID = "categoryId";

    @BindView(R.id.xRecyclerView)
    XRecyclerView mRecyclerView;
    @BindView(R.id.ll_content)
    LinearLayout ll_content;

    private String categoryId;
    private ArticleMultiListAdapter mAdapter;

    public static VideoArticleFragment newInstance(String categoryId) {
        VideoArticleFragment fragment = new VideoArticleFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CATEGORY_ID, categoryId);
        fragment.setArguments(bundle);
        return fragment;
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
        return R.layout.fragment_article;
    }

    @Override
    protected void initInject() {
        DaggerVideoArticleFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .videoArticleFragmentModule(new VideoArticleFragmentModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        categoryId = getArguments().getString(CATEGORY_ID, "");
        mRecyclerView.setLinearLayout();
        mRecyclerView.getRecyclerView().addItemDecoration(new SpaceItemDecoration(mContext));
        mRecyclerView.setOnPullLoadMoreListener(this);

        List<NewsMultiArticleDataBean> lists = new ArrayList<>();
        mAdapter = new ArticleMultiListAdapter(mContext, lists);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void lazyLoad() {
        showLoading();
        mPresenter.loadVideoArticleList(categoryId, "0", true);
    }

    @Override
    public void updateVideoArticleList(List<NewsMultiArticleDataBean> lists) {
        hideLoading();
        mAdapter.setDatas(lists);
    }

    @Override
    public void updateMoreVideoArticleList(List<NewsMultiArticleDataBean> lists) {
        hideLoading();
        mAdapter.appendDatas(lists);
    }

    @Override
    public void onRefresh() {
        showLoading();
        mPresenter.loadVideoArticleList(categoryId, "0", true);
    }

    @Override
    public void onLoadMore() {
        mPresenter.loadMoreVideoArticleList(categoryId, TimeUtil.getCurrentTimeStamp(), true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mAdapter != null) {
            mAdapter = null;
        }
        if (mRecyclerView != null) {
            mRecyclerView.getRecyclerView().setItemAnimator(null);
            mRecyclerView.getRecyclerView().setAdapter(null);
            mRecyclerView = null;
        }
    }

}
