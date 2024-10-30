package com.example.bookshelf.data

import com.example.bookshelf.network.BooksApiService
import javax.inject.Inject

interface BooksRepository {
    suspend fun getBooks(userQuery: String): List<String>
}

class NetworkBooksRepository @Inject constructor(
    private val booksApiService: BooksApiService
) : BooksRepository {

    /**
     * Получить список ссылок на обложки книг, удовлетворяющих запросу пользователя
     * @param userQuery текст запроса пользователя из поисковой строки
     */
    override suspend fun getBooks(userQuery: String): List<String> =
        booksApiService.fetchBooksList(userQuery).items.map {
//      Изменить схему URL для будущей загрузки через AsyncImage
            it.bookInfo.thumbnail.imgSrc.replace("http://", "https://")
        }
}