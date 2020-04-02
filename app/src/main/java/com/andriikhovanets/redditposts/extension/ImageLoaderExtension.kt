package com.andriikhovanets.redditposts.extension

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(imageUri: String?, @DrawableRes placeholder: Int) {
    Glide.with(context)
        .load(imageUri)
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
        .apply(RequestOptions().centerCrop().placeholder(placeholder).error(placeholder))
        .into(this)
}