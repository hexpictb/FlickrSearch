package com.aezhkov.flickrsearch.presentation.feature.search.view

import com.aezhkov.flickrsearch.domain.model.search.PhotoItemModel
import com.arellomobile.mvp.MvpView

interface SearchPhotoView : MvpView {

    fun updateItemsList(items: List<PhotoItemModel>)

    fun showError(throwable: Throwable)

    fun showSuggest(items: List<String>)

    fun showProgress()

    fun hideProgress()

    fun openDetails(model: PhotoItemModel)

    fun updateSuggest(list: List<String>)
}