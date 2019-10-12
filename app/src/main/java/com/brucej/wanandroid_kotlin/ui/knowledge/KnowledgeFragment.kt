package com.brucej.wanandroid_kotlin.ui.knowledge

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.brucej.wanandroid_kotlin.R
import com.brucej.wanandroid_kotlin.base.BaseFragment
import com.brucej.wanandroid_kotlin.core.beans.KnowledgeBean
import com.brucej.wanandroid_kotlin.ui.knowledge.adapters.KnowledgeListAdapter
import kotlinx.android.synthetic.main.fragment_knowledge.*

class KnowledgeFragment private constructor() :
    BaseFragment<KnowledgePresenter, KnowledgeView, KnowledgeModel>(), KnowledgeView {

    companion object {
        fun getFragment() = KnowledgeFragment()
    }

    override fun getLayoutId() = R.layout.fragment_knowledge
    override fun createPresenter() = KnowledgePresenter()

    private var knowledgeList = ArrayList<KnowledgeBean>()
    private lateinit var knowledgeListAdapter: KnowledgeListAdapter
    override fun initDataAndEvent(state: Bundle?) {
        mPresenter?.let {
            it.mModel = KnowledgeModel()
            it.attachView(this)
        }
        recycleView?.let {
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            knowledgeListAdapter =
                KnowledgeListAdapter(R.layout.item_knowledge_list, knowledgeList)
            it.adapter = knowledgeListAdapter
        }
        srl?.run {
            setEnableRefresh(true)
            setEnableLoadMore(false)
            setOnRefreshListener { refreshLayout ->
                mPresenter?.getKnowledgeList()
            }
            autoRefresh()
        }
    }

    override fun showKnowledgeList(data: List<KnowledgeBean>) {
        knowledgeListAdapter.replaceData(data)
        srl?.finishRefresh()
    }
}