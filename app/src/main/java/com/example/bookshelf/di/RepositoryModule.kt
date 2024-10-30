package com.example.bookshelf.di

import com.example.bookshelf.data.BooksRepository
import com.example.bookshelf.data.NetworkBooksRepository
import dagger.Binds
import dagger.Module


/**
 * Из-за @Binds [RepositoryModule] должен быть абстрактным классом.
 */
@Module
abstract class RepositoryModule {

    /**
     * Dagger предоставит экзмепляр [NetworkBooksRepository],
     * когда запрашивается [BooksRepository]
     */
    @Binds
    abstract fun provideBooksRepository(repository: NetworkBooksRepository)
            : BooksRepository
}