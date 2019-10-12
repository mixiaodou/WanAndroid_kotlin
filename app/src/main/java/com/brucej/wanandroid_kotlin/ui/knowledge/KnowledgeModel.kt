package com.brucej.wanandroid_kotlin.ui.knowledge

import com.brucej.wanandroid_kotlin.base.BaseModel
import com.brucej.wanandroid_kotlin.core.beans.KnowledgeBean
import com.brucej.wanandroid_kotlin.core.net.CoroutinueDataHandler
import com.brucej.wanandroid_kotlin.core.net.DataCallback
import com.brucej.wanandroid_kotlin.core.net.RetrofitHelper

class KnowledgeModel : BaseModel() {

    fun getKnowledgeList(callback: KnowledgeListCallback) {
        val deferred = RetrofitHelper.instance.getRestApi().getKnowledgeList()
        addJob(deferred)
        CoroutinueDataHandler.handler(deferred, callback)
    }

    interface KnowledgeListCallback : DataCallback<List<KnowledgeBean>>
}