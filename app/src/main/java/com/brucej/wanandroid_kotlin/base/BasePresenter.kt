package com.brucej.wanandroid_kotlin.base

open class BasePresenter<V : BaseIview, M : BaseModel> {
    lateinit var mModel: M
    var mView: V? = null

    fun attachView(view: V) {
        mView = view
    }

    fun detachView() {
        mModel?.let {
            it.cancleJob()
        }
        mView = null
    }
}