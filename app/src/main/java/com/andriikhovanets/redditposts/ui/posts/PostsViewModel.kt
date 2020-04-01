package com.andriikhovanets.redditposts.ui.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriikhovanets.redditposts.data.repository.PostRepository
import com.andriikhovanets.redditposts.data.model.RedditWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostsViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {

    private val _postsData = MutableLiveData<RedditWrapper>()
    val postData = _postsData

    fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            _postsData.postValue(repository.getPosts(10, ""))
        }
    }
}
