package com.aezhkov.flickrsearch.presentation.feature.search.presenter

import com.aezhkov.flickrsearch.domain.interactor.search.SearchPhotoInteractor
import com.aezhkov.flickrsearch.presentation.feature.search.view.SearchPhotoView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import javax.inject.Inject

@InjectViewState
class SearchPhotoPresenter
@Inject constructor(
    private val searchIterator: SearchPhotoInteractor
) : MvpPresenter<SearchPhotoView>() {

}