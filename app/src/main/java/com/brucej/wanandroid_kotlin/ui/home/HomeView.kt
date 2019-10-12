package com.brucej.wanandroid_kotlin.ui.home

import com.brucej.wanandroid_kotlin.base.BaseIview
import com.brucej.wanandroid_kotlin.core.beans.ArticleListBean
import com.brucej.wanandroid_kotlin.core.beans.BannerData

interface HomeView : BaseIview {
    fun showBanner(data: List<BannerData>)
    fun showArticleList(data: ArticleListBean)
}