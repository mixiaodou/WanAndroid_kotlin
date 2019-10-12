package com.brucej.wanandroid_kotlin.ui.knowledge.adapters

import android.os.VibrationEffect
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.brucej.wanandroid_kotlin.R
import com.brucej.wanandroid_kotlin.core.beans.KnowledgeBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_knowledge_list.view.*

class KnowledgeListAdapter(layoutId: Int, list: List<KnowledgeBean>) :
    BaseQuickAdapter<KnowledgeBean, KnowledgeListViewHolder>(layoutId, list) {
    override fun convert(helper: KnowledgeListViewHolder, item: KnowledgeBean?) {
        helper.tittleTv.text = item?.name
        var str = StringBuilder()
        item?.children?.forEach { t: KnowledgeBean.Children? -> str.append(t?.name + " ") }
        helper.contentTv.text = str.toString()
    }
}

class KnowledgeListViewHolder(v: View) : BaseViewHolder(v) {
    @BindView(R.id.item_knowledge_list_tittleTv)
    lateinit var tittleTv: TextView
    @BindView(R.id.item_knowledge_list_contentTv)
    lateinit var contentTv: TextView

    init {
        ButterKnife.bind(this, v)
    }
}