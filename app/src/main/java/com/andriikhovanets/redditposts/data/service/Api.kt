package com.andriikhovanets.redditposts.data.service

import com.andriikhovanets.redditposts.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Api {

    private val client = OkHttpClient().newBuilder()
        .connectTimeout(5,TimeUnit.MINUTES)
        .writeTimeout(5, TimeUnit.MINUTES)
        .readTimeout(5, TimeUnit.MINUTES)
        .build()

    private fun retrofit() : Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.REDDIT_API_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val apiService : ApiService = retrofit().create(ApiService::class.java)

}