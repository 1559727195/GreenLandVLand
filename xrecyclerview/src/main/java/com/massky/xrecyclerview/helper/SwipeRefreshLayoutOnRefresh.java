package com.massky.xrecyclerview.helper;


import com.massky.xrecyclerview.XRecyclerView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class SwipeRefreshLayoutOnRefresh implements SwipeRefreshLayout.OnRefreshListener {
    XRecyclerView mPullLoadMoreRecyclerView;

    public SwipeRefreshLayoutOnRefresh(XRecyclerView pullLoadMoreRecyclerView) {
        this.mPullLoadMoreRecyclerView = pullLoadMoreRecyclerView;
    }

    @Override
    public void onRefresh() {
        if (!mPullLoadMoreRecyclerView.isRefresh()) {
            mPullLoadMoreRecyclerView.setIsRefresh(true);
            mPullLoadMoreRecyclerView.refresh();
        }
    }
}
