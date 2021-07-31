package com.yash2108.openissuesreader.di.modules

import android.app.Application
import android.content.Context
import android.util.Log
import com.yash2108.openissuesreader.network.service.RetrofitAPI
import com.yash2108.openissuesreader.ui.di.scopes.ApplicationContext
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.io.File
import javax.inject.Inject

@Module
class OkHttpClientModule constructor() {

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
    fun providesFile(@ApplicationContext context: Context): File {
        val file = File(context.cacheDir, "HttpCache")
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