package com.aezhkov.flickrsearch.domain.interactor.search

import io.reactivex.Single

interface SuggestInteractor {

    fun getSuggests(): Single<List<String>>

    fun saveAsSuggest(text:String): Single<Unit>
}