package com.andriikhovanets.redditposts.ui.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriikhovanets.redditposts.Constant.EMPTY
import com.andriikhovanets.redditposts.Constant.POSTS_LIMIT
import com.andriikhovanets.redditposts.data.model.RedditPost
import com.andriikhovanets.redditposts.data.model.RedditWrapper
import com.andriikhovanets.redditposts.data.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostsViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {

    private val _postsData = MutableLiveData<List<RedditPost>>()
    val postData = _postsData

    private var lastPost: RedditPost? = null

    fun loadPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            val wrapper = repository.getPosts(POSTS_LIMIT, nextAfter(lastPost))
            val posts = listFromWrapper(wrapper)
            lastPost = posts.lastOrNull()
            _postsData.postValue(posts)
        }
    }

    private fun listFromWrapper(wrapper: RedditWrapper?): List<RedditPost> {
        val posts = mutableListOf<RedditPost>()
        wrapper?.data?.children?.map { list ->
            list.data?.let { posts.add(it) }
        }
        return posts.toList()
    }

    private fun nextAfter(lastPost: RedditPost?): String? {
        return when (lastPost) {
            null -> EMPTY
            else -> lastPost.name
        }
    }
}
