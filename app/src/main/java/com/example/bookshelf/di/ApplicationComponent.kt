package com.example.bookshelf.di

import com.example.bookshelf.ui.BooksListViewModel
import dagger.Component

/**
 * Определение графа зависимостей
 */
@Component(modules = [NetworkModule::class, RepositoryModule::class])
interface ApplicationComponent {
    fun viewModel(): BooksListViewModel
}



