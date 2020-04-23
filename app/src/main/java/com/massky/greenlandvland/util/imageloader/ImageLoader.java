package com.massky.greenlandvland.util.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.massky.greenlandvland.util.imageloader.glide.GlideImageLoaderStrategy;
import com.massky.greenlandvland.util.imageloader.glide.GlideImageOptions;


/**
 * Created by chenxz on 2017/12/18.
 * <p>
 * 图片加载类：
 * 策略模式和建造者模式封装 ImageLoader ，默认使用 Glide
 */

public final class ImageLoader {

    private BaseImageLoaderStrategy mStrategy;
    private static ImageLoader instance;

    public static ImageLoader getInstance() {
        if (instance == null) {
            synchronized (ImageLoader.class) {
                if (instance == null) {
                    instance = new ImageLoader();
                }
            }
        }
        return instance;
    }

    private ImageLoader() {
        // 默认使用 Glide
        this.mStrategy = new GlideImageLoaderStrategy();
    }

    private ImageLoader(BaseImageLoaderStrategy strategy) {
        this.mStrategy = strategy;
    }

    /**
     * 获取默认的 Options
     *
     * @param url
     * @param imageView
     * @return
     */
    public ImageOptions getDefaultOptions(String url, ImageView imageView) {
        ImageOptions options = null;
        if (mStrategy instanceof GlideImageLoaderStrategy) {
            options = GlideImageOptions.builder()
                    .url(url)
                    .imageView(imageView)
                    .build();
        }
        return options;
    }

    /**
     * 加载图片
     *
     * @param context
     * @param options
     * @param <T>
     */
    public <T extends ImageOptions> void loadImage(Context context, T options) {
        mStrategy.loadImage(context, options);
    }

    /**
     * 停止加载或者清理缓存
     *
     * @param context
     * @param options
     * @param <T>
     */
    public <T extends ImageOptions> void clear(Context context, T options) {
        mStrategy.clear(context, options);
    }

    public void setStrategy(BaseImageLoaderStrategy mStrategy) {
        this.mStrategy = mStrategy;
    }

    public BaseImageLoaderStrategy getStrategy() {
        return mStrategy;
    }
}
