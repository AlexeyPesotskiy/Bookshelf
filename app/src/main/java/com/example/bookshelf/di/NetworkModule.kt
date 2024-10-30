package com.example.bookshelf.di

import com.example.bookshelf.network.BooksApiService
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@Module
class NetworkModule {
    private val baseUrl = "https://www.googleapis.com/books/v1/"

    /**
     * Сообщает Dagger как создать зависимость [BooksApiService]
     */
    @Provides
    fun provideBooksApiService(): BooksApiService =
        Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(baseUrl)
            .build()
            .create(BooksApiService::class.java)
}