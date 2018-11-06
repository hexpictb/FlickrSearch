package com.aezhkov.flickrsearch.presentation.di

import com.aezhkov.flickrsearch.data.di.DataModule
import com.aezhkov.flickrsearch.domain.di.DomainModule
import com.aezhkov.flickrsearch.presentation.feature.search.view.MainActivity
import dagger.Component

@Component(
    modules = [DataModule::class, DomainModule::class]
)
interface AppComponent {

    fun inject(activity: MainActivity)
}