package com.example.bookshelf

import android.app.Application
import com.example.bookshelf.di.ApplicationComponent
import com.example.bookshelf.di.DaggerApplicationComponent

class BookshelfApplication : Application() {
    lateinit var appComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.create()
    }
}