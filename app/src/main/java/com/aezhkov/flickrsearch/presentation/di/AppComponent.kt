package com.aezhkov.flickrsearch.presentation.di

import com.aezhkov.flickrsearch.data.network.HttpClientModule
import com.aezhkov.flickrsearch.data.network.RetrofitModule
import dagger.Component
import retrofit2.Retrofit

@Component(
    modules = [RetrofitModule::class, HttpClientModule::class]
)
interface AppComponent {

    fun provideRetrofit(): Retrofit
}