package com.brucej.wanandroid_kotlin.ui.home

import com.brucej.wanandroid_kotlin.base.BaseModel
import com.brucej.wanandroid_kotlin.core.beans.ArticleListBean
import com.brucej.wanandroid_kotlin.core.beans.BannerData
import com.brucej.wanandroid_kotlin.core.net.CoroutinueDataHandler
import com.brucej.wanandroid_kotlin.core.net.DataCallback
import com.brucej.wanandroid_kotlin.core.net.RetrofitHelper
import kotlinx.coroutines.*
import java.lang.Exception

class HomeModule : BaseModel() {

    fun getBannerData(callback: BannerDataCallback) {
//        GlobalScope.launch(Dispatchers.IO) {
//            RetrofitHelper.instance.getRestApi().getBanner().let {
//                try {
//                    jobList?.add(it)
//                    val baseResponse = it.await()
//                    baseResponse?.let {
//                        if (it.errorCode == 200) {
//                            callback.onSuccess(it.data)
//                        } else {
//                            callback.onError(it.errorMsg)
//                        }
//                    }
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                    callback.onFail()
//                }
//            }
//        }
        val deferred = RetrofitHelper.instance.getRestApi().getBanner()
        addJob(deferred)
        CoroutinueDataHandler.handler(deferred, callback)
    }


    interface BannerDataCallback : DataCallback<List<BannerData>>

    fun getArticleListData(page: Int, callback: ArticleListDataCallback) {
        RetrofitHelper.instance.getRestApi().getArticleList(page).let {
            addJob(it)
            CoroutinueDataHandler.handler(it, callback)
        }
    }

    interface ArticleListDataCallback : DataCallback<ArticleListBean>
}