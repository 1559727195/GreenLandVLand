package com.massky.greenlandvland.module.video.detail;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.massky.greenlandvland.R;
import com.massky.greenlandvland.adapter.news.NewsCommentAdapter;
import com.massky.greenlandvland.app.App;
import com.massky.greenlandvland.base.BaseActivity;
import com.massky.greenlandvland.bean.news.NewsCommentBean;
import com.massky.greenlandvland.bean.news.NewsMultiArticleDataBean;
import com.massky.greenlandvland.di.component.DaggerVideoDetailActivityComponent;
import com.massky.greenlandvland.di.module.VideoDetailActivityModule;
import com.massky.greenlandvland.util.SettingUtil;
import com.massky.greenlandvland.util.ShareUtil;
import com.massky.greenlandvland.util.imageloader.ImageLoader;
import com.massky.greenlandvland.widget.SpaceItemDecoration;
import com.massky.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import androidx.core.widget.ContentLoadingProgressBar;
import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class VideoDetailActivity extends BaseActivity<VideoDetailPresenter> implements VideoDetailContract.View, XRecyclerView.PullLoadMoreListener {

    private static String BEAN = "NewsMultiArticleDataBean";

    @BindView(R.id.recycler_view)
    XRecyclerView mRecyclerView;
    @BindView(R.id.loading_progress)
    ContentLoadingProgressBar loadingProgressBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.video_player)
    JZVideoPlayerStandard mJZVideoPlayer;

    private String groupId;
    private String itemId;
    private String videoId;
    private String videoTitle;
    private String shareUrl;

    private NewsMultiArticleDataBean dataBean;
    private NewsCommentAdapter mAdapter;
    private boolean bLoadMore;

    public static void launch(NewsMultiArticleDataBean dataBean) {
        App.getContext().startActivity(new Intent(App.getContext(), VideoDetailActivity.class)
                .putExtra(BEAN, dataBean)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    public void showLoading() {
        loadingProgressBar.show();
        mRecyclerView.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        loadingProgressBar.hide();
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
        return R.layout.activity_video_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initData();

        mRecyclerView.setLinearLayout();
        mRecyclerView.getRecyclerView().addItemDecoration(new SpaceItemDecoration(this));
        mRecyclerView.setOnPullLoadMoreListener(this);
        List<NewsCommentBean.DataBean.CommentBean> lists = new ArrayList<>();
        mAdapter = new NewsCommentAdapter(this, R.layout.item_news_comment, lists);
        mRecyclerView.setAdapter(mAdapter);

        int color = SettingUtil.getInstance().getColor();
        loadingProgressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        loadingProgressBar.show();

        fab.setBackgroundTintList(ColorStateList.valueOf(color));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareUtil.send(VideoDetailActivity.this, videoTitle + "\n" + shareUrl);
            }
        });

        mPresenter.loadCommentList(groupId, itemId);
        mPresenter.loadVideoDetailData(videoId);

    }

    private void initData() {
        Intent intent = getIntent();
        dataBean = intent.getParcelableExtra(BEAN);
        if (null != dataBean.getVideo_detail_info()) {
            if (null != dataBean.getVideo_detail_info().getDetail_video_large_image()) {
                String imageUrl = dataBean.getVideo_detail_info().getDetail_video_large_image().getUrl();
                if (!TextUtils.isEmpty(imageUrl)) {
                    ImageLoader.getInstance().loadImage(App.getContext(), ImageLoader.getInstance().getDefaultOptions(imageUrl, mJZVideoPlayer.thumbImageView));
                }
            }
        }
        this.groupId = dataBean.getGroup_id() + "";
        this.itemId = dataBean.getItem_id() + "";
        this.videoId = dataBean.getVideo_id();
        this.videoTitle = dataBean.getTitle();
        this.shareUrl = dataBean.getDisplay_url();
    }

    @Override
    protected void initInject() {
        DaggerVideoDetailActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .videoDetailActivityModule(new VideoDetailActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
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
    public void setVideoUrl(String url) {
        mJZVideoPlayer.setUp(url, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, videoTitle);

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
