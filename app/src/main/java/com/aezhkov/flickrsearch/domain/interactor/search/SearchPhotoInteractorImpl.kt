package com.aezhkov.flickrsearch.domain.interactor.search

import com.aezhkov.flickrsearch.data.repository.search.SearchPhotoRepository
import com.aezhkov.flickrsearch.domain.model.search.PhotoItemModel
import io.reactivex.Single
import javax.inject.Inject

private const val PHOTO_URL_PATTERN_SMALL = "https://farm%s.staticflickr.com/%s/%s_%s_s.jpg"
private const val PHOTO_URL_PATTERN_MEDIUM = "https://farm%s.staticflickr.com/%s/%s_%s_b.jpg"

class SearchPhotoInteractorImpl
@Inject constructor(
    private val repository: SearchPhotoRepository
) : SearchPhotoInteractor {

    override fun searchPhoto(text: String): Single<List<PhotoItemModel>> {
        return repository.searchByName(text).map { list ->
            list.map {
                PhotoItemModel(
                    photoUrlSmall = PHOTO_URL_PATTERN_SMALL.format(it.farm, it.server, it.id, it.secret),
                    photoUrlMedium = PHOTO_URL_PATTERN_MEDIUM.format(it.farm, it.server, it.id, it.secret)
                )
            }
        }
    }
}