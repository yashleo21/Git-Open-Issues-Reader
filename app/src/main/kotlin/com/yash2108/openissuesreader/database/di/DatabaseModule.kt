package com.yash2108.openissuesreader.database.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.yash2108.openissuesreader.database.AppDatabase
import com.yash2108.openissuesreader.database.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DatabaseModule @Inject constructor(val application: Application) {

    @Singleton
    @Provides
    fun provideDatabase(): AppDatabase {
       return Room.databaseBuilder(application, AppDatabase::class.java, Constants.DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    fun providesHomeDao(database: AppDatabase) = database.homeDao()
}