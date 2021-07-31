package com.yash2108.openissuesreader.application.di

import android.app.Application
import android.content.Context
import com.squareup.moshi.Moshi
import com.yash2108.openissuesreader.application.MyApplication
import com.yash2108.openissuesreader.network.di.OkHttpClientModule
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module(includes = arrayOf(OkHttpClientModule::class))
class AppModule(private val application: MyApplication) {

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = application

    @Provides
    @Singleton
    fun providesApplication(): Application = application


    @Provides
    @Singleton
    fun providesMoshiInstance(): Moshi = Moshi.Builder().build()
}