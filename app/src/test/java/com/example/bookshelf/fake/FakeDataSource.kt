package com.example.bookshelf.fake

import com.example.bookshelf.network.BookImageLink
import com.example.bookshelf.network.BookInfo
import com.example.bookshelf.network.BookItem
import com.example.bookshelf.network.BookList

object FakeDataSource {
    private const val imgOne = "http://url.1"
    private const val imgTwo = "https://url.2"
    private const val imgThree = "http://url.3"
    private val imgList = listOf(imgOne, imgTwo, imgThree)

    private val booksListItems = listOf(
        BookItem(
            BookInfo(
                BookImageLink(
                    imgSrc = imgList[0]
                )
            )
        ),
        BookItem(
            BookInfo(
                BookImageLink(
                    imgSrc = imgList[1]
                )
            )
        ),
        BookItem(
            BookInfo(
                BookImageLink(
                    imgSrc = imgList[2]
                )
            )
        )
    )

    val booksList = BookList(items = booksListItems)
    val imgListResult = imgList.map {
        it.replace("http://", "https://")
    }
}