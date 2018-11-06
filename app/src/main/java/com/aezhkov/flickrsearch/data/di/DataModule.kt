package com.aezhkov.flickrsearch.data.di

import com.aezhkov.flickrsearch.data.api.PhotosApi
import com.aezhkov.flickrsearch.data.network.HttpClientModule
import com.aezhkov.flickrsearch.data.network.RetrofitModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [DataModuleBinding::class, RetrofitModule::class, HttpClientModule::class])
class DataModule {

    @Provides
    @Singleton
    fun provideSearchApi(retrofit: Retrofit): PhotosApi = retrofit.create(PhotosApi::class.java)
}