package com.aezhkov.flickrsearch.domain.interactor.search

import com.aezhkov.flickrsearch.data.storage.PreferenceStorage
import io.reactivex.Single
import javax.inject.Inject

private const val SUGGESTS_KEY = "suggests_list"

class SuggestInteractorImpl
@Inject constructor(
    private val storage: PreferenceStorage
) : SuggestInteractor {

    override fun getSuggests(): Single<List<String>> {
        return Single.fromCallable { storage.getList(SUGGESTS_KEY).takeLast(10) }
    }

    override fun saveAsSuggest(text: String): Single<Unit> {
        return Single.fromCallable { storage.getList(SUGGESTS_KEY) }
            .map {
                mutableListOf(text).apply { addAll(it) }
            }.flatMap {
                Single.fromCallable {
                    storage.saveList(SUGGESTS_KEY, it)
                }
            }
    }
}