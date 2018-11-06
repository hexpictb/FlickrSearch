package com.aezhkov.flickrsearch.presentation.feature.search.view

import com.aezhkov.flickrsearch.domain.model.search.PhotoItemModel
import com.arellomobile.mvp.MvpView

interface SearchPhotoView : MvpView {

    fun updateItemsList(items: List<PhotoItemModel>)

}