package com.aezhkov.flickrsearch.domain.di

import com.aezhkov.flickrsearch.domain.interactor.search.SearchPhotoInteractor
import com.aezhkov.flickrsearch.domain.interactor.search.SearchPhotoInteractorImpl
import com.aezhkov.flickrsearch.domain.interactor.search.SuggestInteractor
import com.aezhkov.flickrsearch.domain.interactor.search.SuggestInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface DomainModuleBinding {

    @Binds
    fun provideSearchPhotoInteractor(interactor: SearchPhotoInteractorImpl): SearchPhotoInteractor

    @Binds
    fun provideSuggestInteractor(interactor: SuggestInteractorImpl): SuggestInteractor
}