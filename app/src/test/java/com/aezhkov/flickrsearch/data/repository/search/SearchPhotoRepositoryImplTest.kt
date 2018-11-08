package com.aezhkov.flickrsearch.data.repository.search

import com.aezhkov.flickrsearch.data.api.PhotosApi
import com.aezhkov.flickrsearch.data.dto.search.PhotoInfo
import com.aezhkov.flickrsearch.data.dto.search.PhotosData
import com.aezhkov.flickrsearch.data.dto.search.PhotosResponse
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Test

class SearchPhotoRepositoryImplTest {
    private val api = mockk<PhotosApi>()
    private val repository = SearchPhotoRepositoryImpl(api)

    @Test
    fun `should call api and map to list`() {
        val photoInfo = PhotoInfo(
            id = "id",
            farm = "farm",
            secret = "secret",
            server = "server"
        )

        val response = PhotosResponse(
            photos = PhotosData(
                photo = listOf(photoInfo)
            )
        )
        every { api.searchPhotos(any()) } returns Single.just(response)

        val testObserver = repository.searchByName("test query").test()

        testObserver.assertComplete()
        testObserver.assertResult(listOf(photoInfo))

        verify { api.searchPhotos("test query") }
    }
}