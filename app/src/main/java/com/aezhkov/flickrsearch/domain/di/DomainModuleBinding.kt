package com.aezhkov.flickrsearch.domain.di

import com.aezhkov.flickrsearch.domain.interactor.search.SearchPhotoInteractor
import com.aezhkov.flickrsearch.domain.interactor.search.SearchPhotoInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface DomainModuleBinding {

    @Binds
    fun provideSearchPhotoInteractor(interactor: SearchPhotoInteractorImpl): SearchPhotoInteractor
}