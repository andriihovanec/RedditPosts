package com.andriikhovanets.redditposts.ui.posts

import android.net.Uri
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.andriikhovanets.redditposts.App
import com.andriikhovanets.redditposts.R
import com.andriikhovanets.redditposts.data.model.RedditPost
import com.andriikhovanets.redditposts.ui.core.BaseListFragment
import javax.inject.Inject

class PostsFragment : BaseListFragment(R.layout.fragment_reddit_list) {

    lateinit var viewModel: PostsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewAdapter = PostsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[PostsViewModel::class.java]
        viewModel.loadPosts()
        viewModel.postData.observe(this, Observer {
            handlePosts(it)
        })

        handleClick()
    }

    private fun handlePosts(posts: List<RedditPost>) {
        viewAdapter.clear()
        viewAdapter.add(posts)
        viewAdapter.notifyDataSetChanged()
    }

    private fun handleClick() {
        setOnItemClickListener { it, v ->
            (it as? RedditPost)?.let {
                CustomTabsIntent.Builder()
                    .setStartAnimations(v.context, R.anim.slide_in_right, R.anim.slide_out_left)
                    .setExitAnimations(v.context, R.anim.slide_in_left, R.anim.slide_out_right)
                    .build()
                    .launchUrl(v.context, Uri.parse(it.url))
            }
        }
    }
}
