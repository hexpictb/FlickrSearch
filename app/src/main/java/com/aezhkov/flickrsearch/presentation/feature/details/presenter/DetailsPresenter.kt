package com.aezhkov.flickrsearch.presentation.feature.details.presenter

import com.aezhkov.flickrsearch.domain.model.search.PhotoItemModel
import com.aezhkov.flickrsearch.presentation.feature.details.view.DetailsView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import javax.inject.Inject

@InjectViewState
class DetailsPresenter
@Inject constructor() : MvpPresenter<DetailsView>() {
    lateinit var model: PhotoItemModel

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showImage(model.photoUrlMedium)
    }
}