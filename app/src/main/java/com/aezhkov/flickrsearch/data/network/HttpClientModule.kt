package com.aezhkov.flickrsearch.data.network

import com.aezhkov.flickrsearch.presentation.utils.Keys
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class HttpClientModule {
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor { chain ->
            val newUrl = chain.request().url().newBuilder()
                .addQueryParameter("api_key", Keys.API_KEY)
                .addQueryParameter("format", "json")
                .addQueryParameter("nojsoncallback", "1")
                .build()

            val newRequest = chain.request().newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(newRequest)
        }
        builder.addNetworkInterceptor(StethoInterceptor())
        return builder.build()
    }
}