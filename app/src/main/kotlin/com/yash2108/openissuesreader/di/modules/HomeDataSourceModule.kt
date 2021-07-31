package com.yash2108.openissuesreader.di.modules

import com.yash2108.openissuesreader.models.HomeDataSource
import com.yash2108.openissuesreader.models.HomeLocalDataSourceImpl
import com.yash2108.openissuesreader.models.HomeRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class HomeDataSourceModule {

    @Binds
    abstract fun provideLocalDataSource(localDataSource: HomeLocalDataSourceImpl): HomeDataSource

    @Binds
    abstract fun provideRemoteDataSource(remoteDataSource: HomeRemoteDataSourceImpl): HomeDataSource
}