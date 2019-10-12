package com.brucej.wanandroid_kotlin.base

import android.view.View
import com.brucej.wanandroid_kotlin.app.WanAndroidApp
import com.brucej.wanandroid_kotlin.toast
import com.google.android.material.snackbar.Snackbar

interface BaseIview {
    fun showToast(msg: String) {
        WanAndroidApp.context?.toast(msg)
    }

    fun showSnackBar(v: View, msg: String) {
    }

    fun showLoading() {}
    fun showLoadingCancle() {}
    fun showFail() {}
}