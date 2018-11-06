package com.aezhkov.flickrsearch.data.api

import com.aezhkov.flickrsearch.data.dto.search.PhotoInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi {

    @GET("/?method=flickr.photos.search")
    fun searchPhotos(@Query("text") text: String): Single<List<PhotoInfo>>
}