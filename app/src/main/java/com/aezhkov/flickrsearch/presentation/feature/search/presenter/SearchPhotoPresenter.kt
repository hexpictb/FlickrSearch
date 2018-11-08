package com.aezhkov.flickrsearch.presentation.feature.search.presenter

import com.aezhkov.flickrsearch.domain.interactor.search.SearchPhotoInteractor
import com.aezhkov.flickrsearch.domain.interactor.search.SuggestInteractor
import com.aezhkov.flickrsearch.presentation.feature.search.view.SearchPhotoView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class SearchPhotoPresenter
@Inject constructor(
    private val searchIterator: SearchPhotoInteractor,
    private val suggestInteractor: SuggestInteractor
) : MvpPresenter<SearchPhotoView>() {
    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        disposable.add(
            suggestInteractor.getSuggests()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ viewState.showSuggest(it) }, {})
        )
    }

    fun textInputted(text: String) {
        searchPhotoByText(text)
        disposable.add(
            suggestInteractor.saveAsSuggest(text)
                .flatMap { suggestInteractor.getSuggests() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ viewState.showSuggest(it) }, { })
        )
        viewState.updateItemsList(listOf())
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

    fun suggestSelected(suggest: String) {
        searchPhotoByText(suggest)
    }

    private fun searchPhotoByText(text: String) {
        disposable.add(
            searchIterator.searchPhoto(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ viewState.updateItemsList(it) }, { viewState.showError(it) })
        )
    }
}