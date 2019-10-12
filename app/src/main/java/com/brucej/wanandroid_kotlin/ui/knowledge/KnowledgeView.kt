package com.brucej.wanandroid_kotlin.ui.knowledge

import com.brucej.wanandroid_kotlin.base.BaseIview
import com.brucej.wanandroid_kotlin.core.beans.KnowledgeBean

interface KnowledgeView : BaseIview {
    fun showKnowledgeList(data: List<KnowledgeBean>)
}