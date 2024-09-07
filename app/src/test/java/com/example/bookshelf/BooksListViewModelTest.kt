package com.example.bookshelf

import com.example.bookshelf.fake.FakeDataSource
import com.example.bookshelf.fake.FakeNetworkBooksRepository
import com.example.bookshelf.ui.screens.BooksListUiState
import com.example.bookshelf.ui.screens.BooksListViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Тесты для [BooksListViewModel]
 */
class BooksListViewModelTest {

    @Test
    fun booksListViewModel_getBooksList_verifyUiStateSuccess() =
        runTest {
            val booksListViewModel = BooksListViewModel(
                booksRepository = FakeNetworkBooksRepository()
            )
            assertEquals(
                BooksListUiState.Success(FakeDataSource.imgListResult),
                booksListViewModel.booksListUiState.value
            )
        }
}