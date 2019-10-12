package com.brucej.wanandroid_kotlin.ui.knowledge

import com.brucej.wanandroid_kotlin.base.BasePresenter
import com.brucej.wanandroid_kotlin.core.beans.KnowledgeBean

class KnowledgePresenter : BasePresenter<KnowledgeView, KnowledgeModel>() {
    fun getKnowledgeList() = mModel.getKnowledgeList(object : KnowledgeModel.KnowledgeListCallback {
        override fun onSuccess(data: List<KnowledgeBean>) {
            mView?.showKnowledgeList(data)
        }

        override fun onError(errorMsg: String) {
            mView?.showToast(errorMsg)
        }

        override fun onFail() {
        }
    })
}