package com.andriikhovanets.redditposts.data.service

import com.andriikhovanets.redditposts.BuildConfig
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceFactory {

    fun makeService(isDebug: Boolean): ApiService {
        val okHttpClient = makeOkHttpClient(makeLoggingInterceptor(isDebug))
        return makeService(okHttpClient, Gson())
    }

    private fun makeService(okHttpClient: OkHttpClient, gson: Gson): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.REDDIT_API_ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    private fun makeOkHttpClient(httpLoginInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoginInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }
}