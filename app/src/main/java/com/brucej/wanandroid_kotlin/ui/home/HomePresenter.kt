package com.brucej.wanandroid_kotlin.ui.home

import com.brucej.wanandroid_kotlin.base.BasePresenter
import com.brucej.wanandroid_kotlin.core.beans.ArticleListBean
import com.brucej.wanandroid_kotlin.core.beans.BannerData

class HomePresenter : BasePresenter<HomeView, HomeModule>() {

    fun getBanner() = this.let {
        mModel.getBannerData(object : HomeModule.BannerDataCallback {
            override fun onSuccess(data: List<BannerData>) {
                mView?.showBanner(data)
            }

            override fun onError(errorMsg: String) {
                mView?.showToast(errorMsg)
            }

            override fun onFail() {
            }
        })
    }

    fun getArticleList(page: Int = 0) = this.run {
        mModel.getArticleListData(page, object : HomeModule.ArticleListDataCallback {
            override fun onSuccess(data: ArticleListBean) {
                mView?.showArticleList(data)
            }

            override fun onError(errorMsg: String) {
                mView?.showToast(errorMsg)
            }

            override fun onFail() {
            }
        })
    }
}