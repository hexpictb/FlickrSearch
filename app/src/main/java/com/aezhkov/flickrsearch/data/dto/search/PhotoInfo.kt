package com.aezhkov.flickrsearch.data.dto.search

import com.google.gson.annotations.SerializedName

data class PhotoInfo(
    @SerializedName("id")
    val id: String,
    @SerializedName("secret")
    val secret: String,
    @SerializedName("server")
    val server: String,
    @SerializedName("farm")
    val farm: String


)