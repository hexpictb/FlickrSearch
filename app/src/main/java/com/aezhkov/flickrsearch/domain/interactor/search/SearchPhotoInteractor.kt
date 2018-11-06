package com.aezhkov.flickrsearch.domain.interactor.search

import com.aezhkov.flickrsearch.domain.model.search.PhotoItemModel
import io.reactivex.Single

interface SearchPhotoInteractor {

    fun searchPhoto(text: String): Single<List<PhotoItemModel>>
}