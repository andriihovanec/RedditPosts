package com.andriikhovanets.redditposts.ui

import android.app.Application
import com.andriikhovanets.redditposts.di.AppModule
import com.andriikhovanets.redditposts.di.RemoteModule
import com.andriikhovanets.redditposts.di.ViewModelModule
import com.andriikhovanets.redditposts.ui.posts.PostsFragment
import dagger.Component
import javax.inject.Singleton

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this)).build()
    }
}

@Singleton
@Component(modules = [AppModule::class, RemoteModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: PostsFragment)
}