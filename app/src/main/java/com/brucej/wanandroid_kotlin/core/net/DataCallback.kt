package com.brucej.wanandroid_kotlin.core.net

interface DataCallback<T> {
    fun onSuccess(data: T)
    fun onError(errorMsg: String)
    fun onFail()
}