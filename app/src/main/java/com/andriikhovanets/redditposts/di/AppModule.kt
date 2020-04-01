package com.andriikhovanets.redditposts.di

import android.content.Context
import com.andriikhovanets.redditposts.data.repository.PostRepository
import com.andriikhovanets.redditposts.data.service.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideAppContext(): Context = context

    @Provides
    @Singleton
    fun providePostsRepository(apiService: ApiService): PostRepository {
        return PostRepository(apiService)
    }
}