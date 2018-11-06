package com.aezhkov.flickrsearch

import android.app.Application
import com.aezhkov.flickrsearch.presentation.di.AppComponent
import com.aezhkov.flickrsearch.presentation.di.DaggerAppComponent
import com.facebook.stetho.Stetho



class FlickrSearchApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()

            .build()
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}