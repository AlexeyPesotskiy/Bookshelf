package com.example.bookshelf.data

import com.example.bookshelf.network.BooksApiService

interface BooksRepository {
    suspend fun getBooksList(): List<String>
}

class NetworkBooksRepository(
    private val booksApiService: BooksApiService
) : BooksRepository {

//    Вернуть список ссылок на обложки книг
    override suspend fun getBooksList(): List<String> =
        booksApiService.fetchBooksList().items.map {
//      Изменить схему URL для будущей загрузки через AsyncImage
            it.bookInfo.thumbnail.imgSrc.replace("http://", "https://")
        }
}