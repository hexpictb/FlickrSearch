package com.aezhkov.flickrsearch.domain.interactor.search

import com.aezhkov.flickrsearch.data.dto.search.PhotoInfo
import com.aezhkov.flickrsearch.data.repository.search.SearchPhotoRepository
import com.aezhkov.flickrsearch.domain.model.search.PhotoItemModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Test

class SearchPhotoInteractorImplTest {
    private val repository = mockk<SearchPhotoRepository>(relaxed = true)
    private val interactor = SearchPhotoInteractorImpl(repository)

    @Test
    fun `should load photo list and map to models list`() {
        val photoInfo = PhotoInfo(
            id = "id",
            farm = "farm",
            secret = "secret",
            server = "server"
        )
        every { repository.searchByName(any()) } returns Single.just(listOf(photoInfo))

        val testObserver = interactor.searchPhoto("test query").test()

        val expectedModel = PhotoItemModel(
            photoUrl = "https://farmfarm.staticflickr.com/server/id_secret_s.jpg"
        )
        testObserver.assertComplete()
        testObserver.assertResult(listOf(expectedModel))

        verify { repository.searchByName("test query") }
    }
}