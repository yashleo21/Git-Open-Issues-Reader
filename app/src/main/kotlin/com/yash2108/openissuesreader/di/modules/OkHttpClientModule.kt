package com.yash2108.openissuesreader.di.modules

import android.app.Application
import android.content.Context
import android.util.Log
import com.yash2108.openissuesreader.network.service.RetrofitAPI
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.io.File
import javax.inject.Inject

@Module
class OkHttpClientModule @Inject constructor(val application: Application) {

    @Provides
    fun providesOkhttpClient(cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient()
            .newBuilder()
            .cache(cache)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    fun providesCache(cacheFile: File) = Cache(cacheFile, 10 * 1000 * 1000L)

    @Provides
    fun providesFile(): File {
        val file = File(application.cacheDir, "HttpCache")
        file.mkdirs()
        return file
    }

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.d("Okhttp", it)
        })

        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }

}