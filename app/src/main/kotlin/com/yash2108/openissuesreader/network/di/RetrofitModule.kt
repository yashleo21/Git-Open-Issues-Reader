package com.yash2108.openissuesreader.network.di

import android.app.Application
import com.squareup.moshi.Moshi
import com.yash2108.openissuesreader.application.MyApplication
import com.yash2108.openissuesreader.network.service.RetrofitAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
class RetrofitModule constructor() {

    @Provides
    @Singleton
    fun providesRetrofitApi(retrofit: Retrofit) = retrofit.create(RetrofitAPI::class.java)

    @Provides
    @Singleton
    fun providesRetrofitClient(okhttpClient: OkHttpClient,
                               converterFactory: MoshiConverterFactory) =
        Retrofit.Builder()
            .client(okhttpClient)
            .baseUrl("https://github.com/")
            .addConverterFactory(converterFactory)
            .build()

    @Provides
    @Singleton
    fun providesMoshiConverterFactory() = MoshiConverterFactory.create()

    @Provides
    @Singleton
    fun providesMoshiInstance(): Moshi = Moshi.Builder().build()
}