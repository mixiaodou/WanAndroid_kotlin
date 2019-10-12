package com.brucej.wanandroid_kotlin.ui

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.youth.banner.loader.ImageLoaderInterface

class BannerGlideImageLoader : ImageLoaderInterface<ImageView> {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        Glide.with(context!!).load(path).into(imageView!!)
    }

    override fun createImageView(context: Context?) = ImageView(context)

}