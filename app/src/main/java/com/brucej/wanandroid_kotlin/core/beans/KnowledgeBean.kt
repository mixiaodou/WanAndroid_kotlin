package com.brucej.wanandroid_kotlin.core.beans

data class KnowledgeBean(
    var children: List<Children>,
    var courseId: Int,
    var id: Int,
    var name: String,
    var order: Int,
    var parentChapterId: Int,
    var userControlSetTop: Boolean,
    var visible: Int
) {
    data class Children(
        var children: List<Any>,
        var courseId: Int,
        var id: Int,
        var name: String,
        var order: Int,
        var parentChapterId: Int,
        var userControlSetTop: Boolean,
        var visible: Int
    )
}

