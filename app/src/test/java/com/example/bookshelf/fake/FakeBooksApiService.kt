package com.example.bookshelf.fake

import com.example.bookshelf.network.BookList
import com.example.bookshelf.network.BooksApiService

class FakeBooksApiService : BooksApiService {
    override suspend fun fetchBooksList(userQuery: String): BookList =
        FakeDataSource.booksList
}