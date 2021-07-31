package com.yash2108.openissuesreader.application

import android.app.Application
import com.yash2108.openissuesreader.di.modules.AppModule
import com.yash2108.openissuesreader.di.components.ApplicationComponent
import com.yash2108.openissuesreader.application.di.DaggerApplicationComponent

class MyApplication: Application() {

    lateinit var appComponent: ApplicationComponent

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        lateinit var instance: MyApplication

        fun getApplicationInstance() = instance
    }
}