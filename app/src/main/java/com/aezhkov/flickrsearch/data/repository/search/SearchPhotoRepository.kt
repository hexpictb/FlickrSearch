package com.aezhkov.flickrsearch.data.repository.search

import com.aezhkov.flickrsearch.data.dto.search.PhotoInfo
import io.reactivex.Single

interface SearchPhotoRepository {

    fun searchByName(text: String): Single<List<PhotoInfo>>
}