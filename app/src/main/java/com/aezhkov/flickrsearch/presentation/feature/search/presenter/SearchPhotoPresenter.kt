package com.aezhkov.flickrsearch.presentation.feature.search.presenter

import com.aezhkov.flickrsearch.domain.interactor.search.SearchPhotoInteractor
import com.aezhkov.flickrsearch.presentation.feature.search.view.SearchPhotoView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class SearchPhotoPresenter
@Inject constructor(
    private val searchIterator: SearchPhotoInteractor
) : MvpPresenter<SearchPhotoView>() {

    override fun onFirstViewAttach() {
        searchIterator.searchPhoto("bird")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ viewState.updateItemsList(it) }, {})
    }
}