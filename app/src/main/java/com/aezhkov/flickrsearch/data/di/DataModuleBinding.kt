package com.aezhkov.flickrsearch.data.di

import com.aezhkov.flickrsearch.data.repository.search.SearchPhotoRepository
import com.aezhkov.flickrsearch.data.repository.search.SearchPhotoRepositoryImpl
import com.aezhkov.flickrsearch.data.storage.PreferenceStorage
import com.aezhkov.flickrsearch.data.storage.PreferenceStorageImpl
import dagger.Binds
import dagger.Module

@Module
interface DataModuleBinding {

    @Binds
    fun provideSearchRepository(repo: SearchPhotoRepositoryImpl): SearchPhotoRepository

    @Binds
    fun providePreferenseStorage(storage: PreferenceStorageImpl): PreferenceStorage
}