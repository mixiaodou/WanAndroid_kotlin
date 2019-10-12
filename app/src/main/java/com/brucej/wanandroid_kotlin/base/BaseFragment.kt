package com.brucej.wanandroid_kotlin.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment<P : BasePresenter<V, M>, V : BaseIview, M : BaseModel> : Fragment() {
    val TAG = this::class.java.simpleName + "--"
    val mPresenter: P? by lazy {
        createPresenter()
    }

    open fun createPresenter(): P? {
        return null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(getLayoutId(), container, false)
    }

    abstract fun getLayoutId(): Int

    abstract fun initDataAndEvent(state: Bundle?)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDataAndEvent(savedInstanceState)
    }
}