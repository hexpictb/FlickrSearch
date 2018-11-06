package com.aezhkov.flickrsearch.data.repository.search

import com.aezhkov.flickrsearch.data.api.PhotosApi
import com.aezhkov.flickrsearch.data.dto.search.PhotoInfo
import io.reactivex.Single
import javax.inject.Inject

class SearchPhotoRepositoryImpl
@Inject constructor(
    private val photosApi: PhotosApi
) : SearchPhotoRepository {

    override fun searchByName(text: String): Single<List<PhotoInfo>> {
        return photosApi.searchPhotos(text)
            .map {
                it.photos.photo
            }
    }
}