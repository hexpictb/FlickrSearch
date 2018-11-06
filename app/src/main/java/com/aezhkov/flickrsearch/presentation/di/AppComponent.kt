package com.aezhkov.flickrsearch.presentation.di

import com.aezhkov.flickrsearch.data.di.DataModule
import com.aezhkov.flickrsearch.domain.di.DomainModule
import dagger.Component
import retrofit2.Retrofit

@Component(
    modules = [DataModule::class, DomainModule::class]
)
interface AppComponent {
}