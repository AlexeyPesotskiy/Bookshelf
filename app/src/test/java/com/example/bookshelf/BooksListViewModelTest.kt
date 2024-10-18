package com.example.bookshelf

import com.example.bookshelf.fake.FakeDataSource
import com.example.bookshelf.fake.FakeNetworkBooksRepository
import com.example.bookshelf.ui.BooksListViewModel
import com.example.bookshelf.ui.ResultType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Тесты для [BooksListViewModel]
 */
class BooksListViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun booksListViewModel_getBooksList_verifyUiStateSuccess() = runTest {
        val booksListViewModel = BooksListViewModel(FakeNetworkBooksRepository())
        booksListViewModel.getBooksList()
        advanceUntilIdle()

        assertEquals(
            ResultType.Success(FakeDataSource.imgListResult),
            booksListViewModel.booksListUiState.value.resultType
        )
    }
}