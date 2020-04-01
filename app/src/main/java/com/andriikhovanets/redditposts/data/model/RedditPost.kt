package com.andriikhovanets.redditposts.data.model

import com.google.gson.annotations.SerializedName

data class RedditPost(
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("created")
    var created: Long?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("author")
    var authorName: String?,
    @SerializedName("num_comments")
    var commentsNumber: Int?,
    @SerializedName("subreddit")
    var subRedditName: String?,
    @SerializedName("permalink")
    var permalink: String?,
    @SerializedName("score")
    var score: Int?
)