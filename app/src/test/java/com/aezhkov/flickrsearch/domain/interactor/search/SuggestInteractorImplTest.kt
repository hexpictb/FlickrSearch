package com.aezhkov.flickrsearch.domain.interactor.search

import com.aezhkov.flickrsearch.data.storage.PreferenceStorage
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class SuggestInteractorImplTest {
    private val storage = mockk<PreferenceStorage>(relaxed = true)
    private val interactor = SuggestInteractorImpl(storage)

    @Test
    fun `should call get list method`() {

        every { storage.getList(any()) } returns emptyList()

        val testObserver = interactor.getSuggests().test()

        testObserver.assertComplete()
        testObserver.assertResult(emptyList())
        verify { storage.getList(any()) }
    }
}