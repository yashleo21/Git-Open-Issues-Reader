package com.yash2108.openissuesreader.database.di

import com.yash2108.openissuesreader.database.AppDatabase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(DatabaseModule::class))
interface DatabaseComponent {

    fun getDatabase(): AppDatabase
}