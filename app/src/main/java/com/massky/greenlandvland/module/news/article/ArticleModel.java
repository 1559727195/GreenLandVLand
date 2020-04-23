package com.massky.greenlandvland.module.news.article;



import com.massky.greenlandvland.base.mvp.BaseModel;
import com.massky.greenlandvland.bean.news.NewsMultiArticleBean;
import com.massky.greenlandvland.di.scope.FragmentScope;
import com.massky.greenlandvland.http.cache.NewsCacheProvider;
import com.massky.greenlandvland.http.service.NewsService;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;

/**
 * Created by chenxz on 2017/12/10.
 */
@FragmentScope
public class ArticleModel extends BaseModel implements ArticleContract.Model {

    @Inject
    public ArticleModel() {
    }

    @Override
    public Observable<NewsMultiArticleBean> loadNewsArticleList(final String category, final String minBehotTime, final boolean isUpdate) {
        return Observable.just(mRetrofitHelper.obtainRetrofitService(NewsService.class).getNewsArticleList(category, minBehotTime))
                .flatMap(new Function<Observable<NewsMultiArticleBean>, ObservableSource<NewsMultiArticleBean>>() {
                    @Override
                    public ObservableSource<NewsMultiArticleBean> apply(Observable<NewsMultiArticleBean> observable) throws Exception {
                        return mRetrofitHelper.obtainCacheService(NewsCacheProvider.class)
                                .getNewsArticleList(observable, new DynamicKey(category), new EvictDynamicKey(isUpdate))
                                .map(new Function<Reply<NewsMultiArticleBean>, NewsMultiArticleBean>() {
                                    @Override
                                    public NewsMultiArticleBean apply(Reply<NewsMultiArticleBean> newsMultiArticleBeanReply) throws Exception {
                                        return newsMultiArticleBeanReply.getData();
                                    }
                                });
                    }
                });
    }

    @Override
    public Observable<NewsMultiArticleBean> loadMoreNewsArticleList(final String category, final String maxBehotTime, final boolean isUpdate) {
        return Observable.just(mRetrofitHelper.obtainRetrofitService(NewsService.class).getNewsArticleList(category, maxBehotTime))
                .flatMap(new Function<Observable<NewsMultiArticleBean>, ObservableSource<NewsMultiArticleBean>>() {
                    @Override
                    public ObservableSource<NewsMultiArticleBean> apply(Observable<NewsMultiArticleBean> observable) throws Exception {
                        return mRetrofitHelper.obtainCacheService(NewsCacheProvider.class)
                                .getMoreNewsArticleList(observable, new DynamicKey(category), new EvictDynamicKey(isUpdate))
                                .map(new Function<Reply<NewsMultiArticleBean>, NewsMultiArticleBean>() {
                                    @Override
                                    public NewsMultiArticleBean apply(Reply<NewsMultiArticleBean> newsMultiArticleBeanReply) throws Exception {
                                        return newsMultiArticleBeanReply.getData();
                                    }
                                });
                    }
                });
    }
}
