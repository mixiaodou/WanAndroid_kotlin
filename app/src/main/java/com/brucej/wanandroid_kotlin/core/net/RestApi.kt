package com.brucej.wanandroid_kotlin.core.net

import com.brucej.wanandroid_kotlin.core.beans.ArticleListBean
import com.brucej.wanandroid_kotlin.core.beans.BannerData
import com.brucej.wanandroid_kotlin.core.beans.BaseResponse
import com.brucej.wanandroid_kotlin.core.beans.KnowledgeBean
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApi {
    /**
     *
     * https://www.wanandroid.com/banner/json
     */
    @GET("banner/json")
    fun getBanner(): Deferred<BaseResponse<List<BannerData>>>

    /**
     * https://www.wanandroid.com/article/list/0/json
     */
    @GET("article/list/{page}/json")
    fun getArticleList(@Path("page") page: Int): Deferred<BaseResponse<ArticleListBean>>

    /**
     * 知识体系列表
     * https://www.wanandroid.com/tree/json
     */
    @GET("tree/json")
    fun getKnowledgeList(): Deferred<BaseResponse<List<KnowledgeBean>>>

}