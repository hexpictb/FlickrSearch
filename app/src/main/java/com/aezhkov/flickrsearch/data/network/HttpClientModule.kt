package com.aezhkov.flickrsearch.data.network

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class HttpClientModule {
    @Provides
    @Singleton
    fun provideHttpClient(interceptors: List<Interceptor>): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .apply {
                interceptors.forEach {
                    addInterceptor(it)
                }
            }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideInterceptors(): List<Interceptor> {
        return listOf()
    }
}