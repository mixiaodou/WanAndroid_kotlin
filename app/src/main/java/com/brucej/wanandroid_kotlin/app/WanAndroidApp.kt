package com.brucej.wanandroid_kotlin.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class WanAndroidApp : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
    }

    companion object {
        var context: Context? = null;
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}