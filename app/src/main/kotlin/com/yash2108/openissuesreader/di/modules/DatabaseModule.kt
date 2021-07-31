package com.yash2108.openissuesreader.di.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.yash2108.openissuesreader.database.AppDatabase
import com.yash2108.openissuesreader.database.Constants
import com.yash2108.openissuesreader.ui.di.scopes.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DatabaseModule  {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
       return Room.databaseBuilder(context, AppDatabase::class.java, Constants.DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    fun providesHomeDao(database: AppDatabase) = database.homeDao()
}