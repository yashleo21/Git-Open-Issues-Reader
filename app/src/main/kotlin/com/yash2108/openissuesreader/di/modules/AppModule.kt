package com.yash2108.openissuesreader.di.modules

import android.app.Application
import android.content.Context
import com.yash2108.openissuesreader.application.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(OkHttpClientModule::class))
class AppModule(private val application: MyApplication) {

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = application

    @Provides
    @Singleton
    fun providesApplication(): Application = application
}