package com.example.bookshelf.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

//Список книг
@Serializable
data class BooksList(
    val items: List<BookItem>
)

//Элемент из списка книг
@Serializable
data class BookItem(
    val bookInfo: BookInfo
)

/**
 * Информация о книге
 *
 * @param title заголовок
 * @param imageLink ссылка на обложку
 */
@Serializable
data class BookInfo(
    val title: String,
    @SerialName("imageLink")
    val thumbnail: ImageLink
)

//Ссылка на обложку книги
@Serializable
data class ImageLink(
    @SerialName("thumbnail")
    val imgSrc: String
)