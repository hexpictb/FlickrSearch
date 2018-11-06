package com.aezhkov.flickrsearch

import android.app.Application
import com.aezhkov.flickrsearch.presentation.di.AppComponent
import com.aezhkov.flickrsearch.presentation.di.DaggerAppComponent

class FlickrSearchApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()

            .build()
    }
}