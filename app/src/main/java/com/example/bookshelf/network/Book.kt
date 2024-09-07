package com.example.bookshelf.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//Список книг
@Serializable
data class BookList(
    val items: List<BookItem> = emptyList()
)

//Элемент из списка книг
@Serializable
data class BookItem(
    @SerialName("volumeInfo")
    val bookInfo: BookInfo
)

/**
 * Информация о книге
 *
 * @param imageLink ссылка на обложку
 */
@Serializable
data class BookInfo(
    @SerialName("imageLinks")
    val thumbnail: BookImageLink
)

//Ссылка на обложку книги
@Serializable
data class BookImageLink(
    @SerialName("thumbnail")
    var imgSrc: String
)