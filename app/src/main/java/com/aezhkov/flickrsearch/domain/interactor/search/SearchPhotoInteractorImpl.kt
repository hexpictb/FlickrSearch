package com.aezhkov.flickrsearch.domain.interactor.search

import com.aezhkov.flickrsearch.data.repository.search.SearchPhotoRepository
import com.aezhkov.flickrsearch.domain.model.search.PhotoItemModel
import io.reactivex.Single
import javax.inject.Inject

private const val PHOTO_URL_PATTER = "https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_[mstzb].jpg"

class SearchPhotoInteractorImpl
@Inject constructor(
    private val repository: SearchPhotoRepository
) : SearchPhotoInteractor {

    override fun searchPhoto(text: String): Single<List<PhotoItemModel>> {
        return repository.searchByName(text).map {
            it.map {
                PhotoItemModel(
                    photoUrl = PHOTO_URL_PATTER
                )
            }
        }
    }
}