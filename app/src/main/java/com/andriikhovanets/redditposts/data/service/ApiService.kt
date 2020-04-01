package com.andriikhovanets.redditposts.data.service

import com.andriikhovanets.redditposts.data.model.RedditWrapper
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top.json")
    fun getTopReddits(
        @Query("limit") limit: Int?,
        @Query("after") after: String?
    ): Deferred<Response<RedditWrapper>>
}