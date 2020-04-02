package com.andriikhovanets.redditposts.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andriikhovanets.redditposts.ui.posts.PostsViewModel
import com.andriikhovanets.redditposts.ui.core.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(accountViewModel: PostsViewModel): ViewModel
}