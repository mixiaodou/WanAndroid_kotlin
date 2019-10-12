package com.brucej.wanandroid_kotlin.ui.home


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.brucej.wanandroid_kotlin.R
import com.brucej.wanandroid_kotlin.base.BaseFragment
import com.brucej.wanandroid_kotlin.core.beans.ArticleListBean
import com.brucej.wanandroid_kotlin.core.beans.BannerData
import com.brucej.wanandroid_kotlin.toast
import com.brucej.wanandroid_kotlin.ui.BannerGlideImageLoader
import com.brucej.wanandroid_kotlin.ui.home.adapters.ArticleListAdapter
import com.brucej.wanandroid_kotlin.ui.webbrower.WebBrowerActivity
import com.google.android.material.snackbar.Snackbar
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.transformer.AccordionTransformer
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.async
import kotlin.collections.ArrayList


class HomeFragment private constructor() : BaseFragment<HomePresenter, HomeView, HomeModule>(),
    HomeView {

    companion object {
        fun getFragment() = HomeFragment()
    }

    override fun getLayoutId() = R.layout.fragment_home
    private lateinit var articleList: ArrayList<ArticleListBean.Data>

    private lateinit var articleAdapter: ArticleListAdapter
    private var page = 0;
    private lateinit var banner: Banner
    override fun initDataAndEvent(state: Bundle?) {
        mPresenter?.let {
            it.mModel = HomeModule()
            it.attachView(this)
        }
        articleList = ArrayList()
        articleAdapter = ArticleListAdapter(R.layout.item_aricle_list, articleList)
        articleAdapter.run {
            var bannerParent =
                layoutInflater.inflate(R.layout.layout_banner_home, null) as ViewGroup
            banner = bannerParent.getChildAt(0) as Banner
            articleAdapter.addHeaderView(bannerParent)
            articleAdapter.setOnItemClickListener { adapter, view, position ->
                kotlin.run {
                    val article = articleList[position]
                    context?.let {
                        WebBrowerActivity.skip(it, article.link, article.title)
                    }
                }
            }
        }
        banner.run {
            setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
            setIndicatorGravity(BannerConfig.RIGHT)
            setImageLoader(BannerGlideImageLoader())
            setBannerAnimation(AccordionTransformer::class.java)
            isAutoPlay(true)
            setDelayTime(3000)
        }
        banner.setOnBannerListener { position ->
            val bannerData = bannerList[position]
            context?.let {
                WebBrowerActivity.skip(it, bannerData.url, bannerData.title)
            }
        }
        recycleView.let {
            it.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
            it.adapter = articleAdapter
        }

        srl?.run {
            setEnableRefresh(true)
            setEnableLoadMore(true)
            setEnableAutoLoadMore(true)
            setOnRefreshListener { refreshLayout ->
                refreshLayout.let {
                    mPresenter?.run {
                        getBanner()
                        page = 0
                        getArticleList(page)
                    }
                }
            }
            setOnLoadMoreListener { v ->
                mPresenter?.run {
                    getArticleList(page++)
                }
            }
            autoRefresh()
        }

    }

    lateinit var bannerList: List<BannerData>
    override fun showBanner(data: List<BannerData>) {
        bannerList = data
        Log.i(TAG, "showBanner=$data")
        val tittleList = ArrayList<String>()
        val imgUrlList = ArrayList<String>()
        data?.forEach { t: BannerData ->
            run {
                tittleList.add(t.title)
                imgUrlList.add(t.imagePath)
            }
        }
        banner.setBannerTitles(tittleList)
        banner.setImages(imgUrlList)
        banner.start()
    }

    override fun showArticleList(data: ArticleListBean) {
        Log.i(TAG, "showArticleList=$data")
        page = data.curPage - 1
        when (page) {
            0 -> {
                articleAdapter.replaceData(data.datas)
                srl.finishRefresh()
                srl.finishLoadMore()
            }
            else -> {
                articleAdapter.addData(data.datas)
                srl.finishLoadMore()
            }
        }
        if (data.datas.isNullOrEmpty() && page != 0) {
            srl.finishLoadMoreWithNoMoreData()
        }
    }

    override fun onStart() {
        super.onStart()
        banner?.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        banner?.stopAutoPlay()
    }

    override fun onDestroy() {
        super.onDestroy()
        banner?.releaseBanner()
    }

    override fun createPresenter(): HomePresenter? {
        return HomePresenter()
    }


}
