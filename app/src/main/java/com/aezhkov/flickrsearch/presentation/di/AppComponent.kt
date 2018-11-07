package com.aezhkov.flickrsearch.presentation.di

import com.aezhkov.flickrsearch.data.di.DataModule
import com.aezhkov.flickrsearch.domain.di.DomainModule
import com.aezhkov.flickrsearch.presentation.feature.search.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [AppModule::class, DataModule::class, DomainModule::class]
)
@Singleton
interface AppComponent {

    fun inject(activity: MainActivity)
}