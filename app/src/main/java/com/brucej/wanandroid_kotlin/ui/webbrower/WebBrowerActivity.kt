package com.brucej.wanandroid_kotlin.ui.webbrower

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import com.brucej.wanandroid_kotlin.R
import com.brucej.wanandroid_kotlin.base.BaseActivity
import com.brucej.wanandroid_kotlin.base.BaseIview
import com.brucej.wanandroid_kotlin.base.BaseModel
import com.brucej.wanandroid_kotlin.base.BasePresenter
import com.just.agentweb.AgentWeb
import kotlinx.android.synthetic.main.activity_webbrower.*

class WebBrowerActivity :
    BaseActivity<BasePresenter<BaseIview, BaseModel>, BaseIview, BaseModel>() {
    companion object {
        const val TITTLE = "tittle"
        const val URL = "url"
        fun skip(context: Context, url: String, tittle: String) {
            var intent = Intent(context, WebBrowerActivity::class.java)
            intent.putExtra(TITTLE, tittle)
            intent.putExtra(URL, url)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId() = R.layout.activity_webbrower
    private lateinit var agentWeb: AgentWeb.PreAgentWeb
    override fun initDataAndEvent(state: Bundle?) {
        supportActionBar?.run {
            setSupportActionBar(toolBar)
            setDisplayShowTitleEnabled(false)
        }
        toolBar.setNavigationOnClickListener { v ->
            onBackPressed()
        }
        agentWeb = AgentWeb.with(this)
            .setAgentWebParent(frameLayout, frameLayout.layoutParams)
            .useDefaultIndicator()
            .createAgentWeb()
            .ready()
        agentWeb.get().webCreator.webView.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                title?.run {
                    showTittle(this)
                }
            }
        }
        agentWeb.get().webCreator.webView.settings?.run {
            //缓存设置
            setAppCacheEnabled(true)
            databaseEnabled = true
            domStorageEnabled = true
            blockNetworkImage = true
            //todo 解决 不显示图片问题
            // https://blog.csdn.net/u013320868/article/details/52837671
            // android5.1 开始https中默认不允许使用http加载图片
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                blockNetworkImage = false
            }
            //js
            javaScriptEnabled = true
            //缩放
            setSupportZoom(true)
            displayZoomControls = false
            useWideViewPort = true
            //自适应屏幕
            loadWithOverviewMode = true
            layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        }
        intent.run {
            var tittle = getStringExtra(TITTLE)
            var url = getStringExtra(URL)
            showTittle(tittle)
            agentWeb.go(url)
        }
    }

    private fun showTittle(tittle: String) {
        tittleTv.text = tittle
    }

    override fun onBackPressed() {
        if (agentWeb?.get().back()) {
            return
        }
        super.onBackPressed()
    }
}