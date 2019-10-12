package com.brucej.wanandroid_kotlin.core.beans

data class BaseResponse<T>(
    var data: T,
    var errorCode: Int,
    var errorMsg: String
)

data class BannerData(
    var desc: String,
    var id: Int,
    var imagePath: String,
    var isVisible: Int,
    var order: Int,
    var title: String,
    var type: Int,
    var url: String
)
