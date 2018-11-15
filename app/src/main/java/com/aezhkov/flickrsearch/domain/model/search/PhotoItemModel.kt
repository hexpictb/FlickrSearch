package com.aezhkov.flickrsearch.domain.model.search

import java.io.Serializable

data class PhotoItemModel(
    val photoUrlSmall: String,
    val photoUrlMedium: String
) : Serializable