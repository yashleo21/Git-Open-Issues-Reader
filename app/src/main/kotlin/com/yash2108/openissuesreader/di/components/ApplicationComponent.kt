package com.yash2108.openissuesreader.di.components

import android.content.Context
import com.yash2108.openissuesreader.database.converters.Converters
import com.yash2108.openissuesreader.di.modules.*
import com.yash2108.openissuesreader.ui.di.scopes.ApplicationContext
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    DatabaseModule::class,
    RetrofitModule::class,
    AppSubcomponents::class))
interface ApplicationComponent {

    val homeScreenComponentBuilder: HomeScreenComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance @ApplicationContext applicationContext: Context): ApplicationComponent
    }

    fun inject(converters: Converters)
}