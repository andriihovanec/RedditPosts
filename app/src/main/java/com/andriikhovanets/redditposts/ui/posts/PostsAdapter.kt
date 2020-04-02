package com.andriikhovanets.redditposts.ui.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.andriikhovanets.redditposts.R
import com.andriikhovanets.redditposts.data.model.RedditPost
import com.andriikhovanets.redditposts.extension.loadImage
import com.andriikhovanets.redditposts.ui.core.BaseAdapter
import com.andriikhovanets.redditposts.utils.TimeUtils.getFormattedDate
import com.bumptech.glide.Glide

class PostsAdapter : BaseAdapter<PostsAdapter.PostsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val rootView = layoutInflater.inflate(R.layout.item_reddit, parent, false)
        return PostsViewHolder(rootView)
    }

    class PostsViewHolder(private val rootView: View) : BaseViewHolder(rootView) {

        init {
            rootView.setOnClickListener {
                onClick?.onClick(item, it)
            }
        }

        override fun onBind(item: Any) {
            val context = rootView.context
            (item as RedditPost).let {
                rootView.findViewById<ImageView>(R.id.ivBanner).loadImage(it.url, R.drawable.ic_placeholder)
                Glide.with(context).load(it.url).into(rootView.findViewById(R.id.ivBanner))
                rootView.findViewById<TextView>(R.id.tvAuthor).text = it.authorName
                rootView.findViewById<TextView>(R.id.tvTime).text =
                    context.getString(R.string.time, getFormattedDate(it.created!!))
                rootView.findViewById<TextView>(R.id.tvTitle).text = it.title
                rootView.findViewById<TextView>(R.id.tvNumComments).text =
                    context.getString(R.string.comments, it.commentsNumber)
                rootView.findViewById<TextView>(R.id.tvScore).text = context.getString(R.string.score, it.score)
            }
        }
    }
}