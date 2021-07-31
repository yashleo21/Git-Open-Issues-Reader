package com.yash2108.openissuesreader.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule constructor(private val context: Context) {

    @Provides
    fun context() = context
}