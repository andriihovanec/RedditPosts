package com.andriikhovanets.redditposts.ui.posts

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.andriikhovanets.redditposts.R
import com.andriikhovanets.redditposts.ui.App
import javax.inject.Inject

class PostsFragment : Fragment(R.layout.fragment_reddit_list) {

    lateinit var viewModel: PostsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(PostsViewModel::class.java)

        viewModel = ViewModelProvider(this,viewModelFactory)[PostsViewModel::class.java]
        viewModel.getPosts()
        viewModel.postData.observe(this, Observer {
            Toast.makeText(context, "${it.kind}", Toast.LENGTH_SHORT).show()
        })
    }
}
