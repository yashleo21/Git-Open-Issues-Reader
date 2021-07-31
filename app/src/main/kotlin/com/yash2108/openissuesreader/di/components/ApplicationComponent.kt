package com.yash2108.openissuesreader.di.components

import android.content.Context
import com.yash2108.openissuesreader.database.converters.Converters
import com.yash2108.openissuesreader.di.modules.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    AppModule::class,
    ContextModule::class,
    DatabaseModule::class,
    RetrofitModule::class,
    AppSubcomponents::class))
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(converters: Converters)
}