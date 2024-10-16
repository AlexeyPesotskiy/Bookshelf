package com.example.bookshelf.network

import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {
    @GET("volumes?maxResults=40&fields=items/volumeInfo/imageLinks/thumbnail")
    suspend fun fetchBooksList(@Query("q") userQuery: String): BookList
}