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

    @Test
    fun `should get current list, and new item and store it`() {
        every { storage.getList(any()) } returns listOf("one")
        every { storage.saveList(any(), any()) } returns Unit

        val testObserver = interactor.saveAsSuggest("two").test()

        testObserver.assertComplete()
        testObserver.assertResult(Unit)

        verify { storage.getList(any()) }
        verify { storage.saveList(any(), listOf("two", "one")) }
    }
}