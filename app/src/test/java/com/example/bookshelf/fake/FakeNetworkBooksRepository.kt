package com.example.bookshelf.fake

import com.example.bookshelf.data.BooksRepository

class FakeNetworkBooksRepository : BooksRepository {
    override suspend fun getBooks(): List<String> = FakeDataSource.imgListResult
}