package com.brucej.wanandroid_kotlin.ui

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.bruce.permisson.PermissonUtil
import com.brucej.wanandroid_kotlin.R
import com.brucej.wanandroid_kotlin.base.BaseActivity
import com.brucej.wanandroid_kotlin.base.BaseIview
import com.brucej.wanandroid_kotlin.base.BaseModel
import com.brucej.wanandroid_kotlin.base.BasePresenter
import com.brucej.wanandroid_kotlin.core.net.RetrofitHelper
import com.brucej.wanandroid_kotlin.ui.home.HomeFragment
import com.brucej.wanandroid_kotlin.ui.knowledge.KnowledgeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class MainActivity : BaseActivity<BasePresenter<BaseIview, BaseModel>,
        BaseIview, BaseModel>() {
    override fun getLayoutId() = R.layout.activity_main

    override fun initDataAndEvent(state: Bundle?) {
        initToolBar()
        initDrawerLayout()
        initNavLeft()
        initNavBottom()
    }

    private fun initNavBottom() {
        navBottom.setOnNavigationItemSelectedListener { menuItem ->
            menuItem.let {
                switchFragment(it.itemId)
                true
            }
        }
        navBottom.selectedItemId = R.id.nav_home
    }

    var currentFragmentId: Int = -1
    private var fragmentMap = HashMap<Int, Fragment>()
    private fun switchFragment(itemId: Int) {
        if (fragmentMap[itemId] == null) {
            when (itemId) {
                R.id.nav_home -> {
                    setTittle("首页")
                    fragmentMap[itemId] = HomeFragment.getFragment()
                }
                R.id.nav_knowledge -> {
                    setTittle("知识")
                    fragmentMap[itemId] = KnowledgeFragment.getFragment()
                }
                R.id.nav_wx -> setTittle("公众号")
                R.id.nav_navigation -> setTittle("导航")
                R.id.nav_project -> setTittle("项目")
            }
        }
        fragmentMap[itemId]?.run {
            val transaction = supportFragmentManager.beginTransaction()
            if (!isAdded) {
                transaction.add(R.id.frameLayout, this)
            }
            if (currentFragmentId != -1) {
                fragmentMap[currentFragmentId]?.let {
                    transaction.hide(it)
                }
            }
            currentFragmentId = itemId
            transaction.show(this).commit()
        }
    }

    private fun initNavLeft() {
        navLeft.setNavigationItemSelectedListener { menuItem ->
            menuItem.let {
                when (menuItem.itemId) {
                    R.id.wanAndroid -> {
                        Toast.makeText(this, "wanAndroid", Toast.LENGTH_LONG).show()
                    }
                    R.id.collect -> {
                        Toast.makeText(this, "collect", Toast.LENGTH_LONG).show()
                    }
                    R.id.setting -> {
                        Toast.makeText(this, "setting", Toast.LENGTH_LONG).show()
                    }
                    R.id.aboutUs -> {
                        Toast.makeText(this, "aboutUs", Toast.LENGTH_LONG).show()
                    }
                    R.id.loginOut -> {
                        Toast.makeText(this, "loginOut", Toast.LENGTH_LONG).show()
                    }
                }
                drawerLayout.closeDrawers()
                true
            }
        }
    }

    private fun initDrawerLayout() {
        //kotlin 匿名对象
//        object : DrawerListener {
//            override fun onDrawerStateChanged(newState: Int) {
//
//            }
//
//            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
//            }
//
//            override fun onDrawerClosed(drawerView: View) {
//            }
//
//            override fun onDrawerOpened(drawerView: View) {
//            }
//        };
        drawerLayout.addDrawerListener(
            object : ActionBarDrawerToggle(this, drawerLayout, 0, 0) {
                override fun onDrawerOpened(drawerView: View) {
                    super.onDrawerOpened(drawerView)
                    toolBar.setNavigationIcon(R.drawable.ic_arrow_back_white)
                }

                override fun onDrawerClosed(drawerView: View) {
                    super.onDrawerClosed(drawerView)
                    toolBar.setNavigationIcon(R.drawable.ic_menu_white)
                }
            });

    }

    private fun initToolBar() {
        setSupportActionBar(toolBar)
        supportActionBar?.run {
            setDisplayShowTitleEnabled(false)
        }
        toolBar.setNavigationIcon(R.drawable.ic_menu_white)
        toolBar.setNavigationOnClickListener { v ->
            drawerLayout.run {
                when (isDrawerOpen(Gravity.LEFT)) {
                    true -> closeDrawer(Gravity.LEFT)
                    false -> openDrawer(Gravity.LEFT)
                }
            }
        }
    }

    fun test() {
//        activity_main_tv?.text = "123"
//        activity_main_tv.run {
//            text = "qwe"
//        }
//        activity_main_tv.let {
//            it.text = "qwe1"
//        }
//        //
//        activity_main_tv?.apply {
//            text = "1234"
//        }
//        activity_main_tv.also {
//            it.text = "12345"
//        }
        runBlocking(Dispatchers.Default) {
            val responce = RetrofitHelper.instance
                .getRestApi().getBanner()
                .await()

            runOnUiThread() {
                //activity_main_tv.text=responce.toString();
            }
            println("----currentThread=${Thread.currentThread().name}")
            tittleTv.text = responce.toString();
        }
    }

    private fun setTittle(tittle: String) {
        tittleTv.text = tittle
    }

    override fun onStart() {
        super.onStart()
        PermissonUtil.getInstance().requestPermissons(this,
            PermissonUtil.RequestCallback() { b, strings ->
                {}
            }
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        PermissonUtil.getInstance()
            .onRequestPermissionsResult(requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}
