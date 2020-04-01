package com.andriikhovanets.redditposts.di

import com.andriikhovanets.redditposts.BuildConfig
import com.andriikhovanets.redditposts.data.service.ApiService
import com.andriikhovanets.redditposts.data.service.ServiceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService = ServiceFactory.makeService(BuildConfig.DEBUG)
}