package com.brucej.wanandroid_kotlin.base

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.brucej.wanandroid_kotlin.R
import com.brucej.wanandroid_kotlin.utils.StatusBarUtil

abstract class BaseActivity<P : BasePresenter<V, M>, V : BaseIview, M : BaseModel> :
    AppCompatActivity() {
    val presenter: P? by lazy {
        createPresenter()
    }

    //abstract fun createPresenter(): P
    //有些Activity不必使用 MVP模式
    fun createPresenter(): P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initDataAndEvent(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        //StatusBarUtil.setTransparentForWindow(this)
        StatusBarUtil.setColor(this, Color.parseColor("#00abff"))
    }

    abstract fun getLayoutId(): Int

    abstract fun initDataAndEvent(state: Bundle?)
}
