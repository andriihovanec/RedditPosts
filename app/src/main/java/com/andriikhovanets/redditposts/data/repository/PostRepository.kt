package com.andriikhovanets.redditposts.data.repository

import com.andriikhovanets.redditposts.data.model.RedditWrapper
import com.andriikhovanets.redditposts.data.service.ApiService
import javax.inject.Inject

class PostRepository @Inject constructor(private val api: ApiService) : BaseRepository() {

    suspend fun getPosts(limit: Int, after: String?): RedditWrapper? {
        return apiCall(
            call = { api.getTopReddits(limit, after).await() },
            errorMessage = "Error check status"
        )
    }
}