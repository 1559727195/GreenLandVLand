package com.massky.greenlandvland.adapter.news;

import android.content.Context;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.jakewharton.rxbinding2.view.RxView;
import com.massky.greenlandvland.R;
import com.massky.greenlandvland.bean.news.NewsMultiArticleDataBean;
import com.massky.greenlandvland.module.video.detail.VideoDetailActivity;
import com.massky.greenlandvland.util.ShareUtil;
import com.massky.greenlandvland.util.TimeUtil;
import com.massky.greenlandvland.util.imageloader.ImageLoader;
import com.massky.greenlandvland.util.imageloader.ImageOptions;
import com.massky.greenlandvland.util.imageloader.glide.GlideImageOptions;
import com.massky.xrecyclerview.adapter.base.BaseItemDelegate;
import com.massky.xrecyclerview.adapter.base.BaseViewHolder;
import java.util.concurrent.TimeUnit;

import androidx.appcompat.widget.PopupMenu;
import io.reactivex.functions.Consumer;

/**
 * Created by chenxz on 2017/12/23.
 */

public class ArticleVideoDelegate implements BaseItemDelegate<NewsMultiArticleDataBean> {

    private Context mContext;
    private ImageOptions options;

    public ArticleVideoDelegate(Context context) {
        this.mContext = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_article_list_video;
    }

    @Override
    public boolean isForViewType(NewsMultiArticleDataBean item, int position) {
        return item.isHas_video();
    }

    @Override
    public void convert(BaseViewHolder holder, final NewsMultiArticleDataBean bean, int position) {
        final String title = bean.getTitle();
        String extra = bean.getSource() + " " + bean.getComment_count() + "评论"
                + " " + TimeUtil.getTimeStampAgo(String.valueOf(bean.getBehot_time()));
        holder.setText(R.id.tv_title, title)
                .setText(R.id.tv_extra, extra);

        if (bean.getUser_info() != null && bean.getUser_info().getAvatar_url() != null) {
            ImageView iv_avatar = holder.getView(R.id.iv_avatar);
            options = GlideImageOptions.builder()
                    .url(bean.getUser_info().getAvatar_url())
                    .placeholder(R.mipmap.ic_default_avatar)
                    .imageView(iv_avatar)
                    .build();
            ImageLoader.getInstance().loadImage(mContext, options);
        }

        ImageView iv_content = holder.getView(R.id.iv_content);
        iv_content.setVisibility(View.GONE);
        if (bean.getVideo_detail_info() != null
                && bean.getVideo_detail_info().getDetail_video_large_image() != null
                && bean.getVideo_detail_info().getDetail_video_large_image().getUrl() != null) {
            iv_content.setVisibility(View.VISIBLE);
            options = GlideImageOptions.builder()
                    .url(bean.getVideo_detail_info().getDetail_video_large_image().getUrl())
                    .scaleType(GlideImageOptions.ImageScaleType.CENTER_CROP)
                    .placeholder(R.mipmap.ic_default_pic)
                    .imageView(iv_content)
                    .build();
            ImageLoader.getInstance().loadImage(mContext, options);
        }

        final ImageView iv_dots = holder.getView(R.id.iv_dots);
        RxView.clicks(iv_dots)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        PopupMenu popupMenu = new PopupMenu(mContext, iv_dots, Gravity.END, 0, R.style.MyPopupMenu);
                        popupMenu.inflate(R.menu.menu_share);
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                int itemId = item.getItemId();
                                if (itemId == R.id.action_share) {
                                    ShareUtil.send(mContext, title + "\n" + bean.getShare_url());
                                }
                                return false;
                            }
                        });
                        popupMenu.show();
                    }
                });

        RxView.clicks(holder.getItemView())
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        VideoDetailActivity.launch(bean);
                    }
                });
    }
}
