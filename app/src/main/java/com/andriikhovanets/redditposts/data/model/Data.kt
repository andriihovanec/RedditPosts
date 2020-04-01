package com.andriikhovanets.redditposts.data.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("children")
    val children: List<Children>?,
    @SerializedName("after")
    val after: String?,
    @SerializedName("before")
    val before: Any?
)