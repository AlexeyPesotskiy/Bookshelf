package com.example.bookshelf

import com.example.bookshelf.data.NetworkBooksRepository
import com.example.bookshelf.fake.FakeBooksApiService
import com.example.bookshelf.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Тесты для [NetworkBooksRepository]
 */
class NetworkBooksRepositoryTest {

    @Test
    fun networkBooksRepository_getBooks_verifyBookList() = runTest {
        assertEquals(
            FakeDataSource.imgListResult,
            NetworkBooksRepository(FakeBooksApiService()).getBooks()
        )
    }
}
