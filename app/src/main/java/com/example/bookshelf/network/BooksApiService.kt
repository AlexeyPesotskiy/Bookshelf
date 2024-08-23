package com.example.bookshelf.network

import retrofit2.http.GET

interface BooksApiService {
    @GET("volumes?q=Android Compose 2.0.4.1.")
    suspend fun fetchBooksList(): BooksList
}