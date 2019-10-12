package com.brucej.wanandroid_kotlin.ui.home.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.brucej.wanandroid_kotlin.R
import com.brucej.wanandroid_kotlin.core.beans.ArticleListBean
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_aricle_list.view.*

class ArticleListAdapter(layoutId: Int, list: List<ArticleListBean.Data>) :
    BaseQuickAdapter<ArticleListBean.Data, ArticleListViewHolder>(layoutId, list) {
    override fun convert(helper: ArticleListViewHolder, item: ArticleListBean.Data?) {
        helper.authorTv.text = item?.shareUser ?: ""
        helper.tittleTv.text = item?.chapterName + "/" + item?.superChapterName
        helper.contentTv.text = item?.title
        helper.timeTv.text = item?.niceDate
        if (!item?.tags.isNullOrEmpty()) {
            when (item!!.tags!!.size) {
                1 -> {
                    helper.lable1Tv.text = item.tags[0].name
                    helper.lable1Tv.visibility = View.VISIBLE
                    helper.lable2Tv.visibility = View.GONE
                }
                2 -> {
                    helper.lable1Tv.text = item.tags[0].name
                    helper.lable2Tv.text = item.tags[1].name
                    helper.lable1Tv.visibility = View.VISIBLE
                    helper.lable2Tv.visibility = View.VISIBLE
                }
            }
        } else {
            helper.lable1Tv.visibility = View.GONE
            helper.lable2Tv.visibility = View.GONE
        }
        //Glide.with(mContext).load(helper.authorImg).into("")
    }

}

class ArticleListViewHolder(v: View) : BaseViewHolder(v) {
    @BindView(R.id.item_article_list_authorImg)
    lateinit var authorImg: ImageView
    @BindView(R.id.item_article_list_authorNameTv)
    lateinit var authorTv: TextView
    @BindView(R.id.item_article_list_tittleTv)
    lateinit var tittleTv: TextView
    @BindView(R.id.item_article_list_contentTv)
    lateinit var contentTv: TextView
    @BindView(R.id.item_article_list_timeTv)
    lateinit var timeTv: TextView
    @BindView(R.id.item_article_list_lableTv1)
    lateinit var lable1Tv: TextView
    @BindView(R.id.item_article_list_lableTv2)
    lateinit var lable2Tv: TextView

    init {
        ButterKnife.bind(this, v)
    }
}