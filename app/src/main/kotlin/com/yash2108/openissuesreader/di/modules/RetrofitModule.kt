package com.yash2108.openissuesreader.di.modules

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yash2108.openissuesreader.network.service.RetrofitAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = arrayOf(OkHttpClientModule::class))
class RetrofitModule {

    @Provides
    @Singleton
    fun providesRetrofitApi(retrofit: Retrofit) = retrofit.create(RetrofitAPI::class.java)

    @Provides
    @Singleton
    fun providesRetrofitClient(okhttpClient: OkHttpClient,
                               converterFactory: MoshiConverterFactory) =
        Retrofit.Builder()
            .client(okhttpClient)
            .baseUrl("https://api.github.com/")
            .addConverterFactory(converterFactory)
            .build()

    @Provides
    @Singleton
    fun providesMoshiConverterFactory(moshi: Moshi) = MoshiConverterFactory.create(moshi).asLenient()

    @Provides
    @Singleton
    fun providesMoshiInstance(): Moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}