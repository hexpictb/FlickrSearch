package com.aezhkov.flickrsearch.data.dto.search

import com.google.gson.annotations.SerializedName

class PhotosResponse(
    @SerializedName("photos")
    val photos: PhotosData
)

class PhotosData(
    @SerializedName("photo")
    val photo: List<PhotoInfo>
)
