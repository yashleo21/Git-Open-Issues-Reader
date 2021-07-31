package com.yash2108.openissuesreader.application.di

import com.yash2108.openissuesreader.database.converters.Converters
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface ApplicationComponent {

    fun inject(converters: Converters)
}