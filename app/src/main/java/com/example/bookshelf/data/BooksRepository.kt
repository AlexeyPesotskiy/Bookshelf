package com.example.bookshelf.data

import com.example.bookshelf.network.BookInfo
import com.example.bookshelf.network.BooksApiService

interface BooksRepository {
    suspend fun getBooksList(): List<BookInfo>
}

class NetworkBooksRepository(
    private val booksApiService: BooksApiService
) : BooksRepository {

//    Вернуть список книг с информацией
    override suspend fun getBooksList(): List<BookInfo> =
        booksApiService.fetchBooksList().items.map {
//            Изменить схему URL для будущей загрузки через AsyncImage
            it.bookInfo.apply {
                thumbnail.imgSrc.replace("http://", "https://")
            }
        }
}