package com.yash2108.openissuesreader.di.modules

import com.yash2108.openissuesreader.database.dao.HomeDao
import com.yash2108.openissuesreader.di.modules.DatabaseModule
import com.yash2108.openissuesreader.models.HomeDataSource
import com.yash2108.openissuesreader.models.HomeLocalDataSourceImpl
import com.yash2108.openissuesreader.models.HomeRemoteDataSourceImpl
import com.yash2108.openissuesreader.network.service.RetrofitAPI
import com.yash2108.openissuesreader.repositories.HomeRepository
import com.yash2108.openissuesreader.ui.di.scopes.ViewModelScoped
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(includes = arrayOf(DatabaseModule::class))
class HomeViewModelModule {
    @Provides
    fun providesHomeRepository(@Named("local") localDataSource: HomeDataSource,
                               @Named("remote") remoteDataSource: HomeDataSource,
                               dao: HomeDao): HomeRepository {
        return HomeRepository(localDataSource, remoteDataSource, dao)
    }

    @Provides
    @Named("local")
    fun providesLocalDataSource(dao: HomeDao): HomeDataSource {
        return HomeLocalDataSourceImpl(dao)
    }

    @Provides
    @Named("remote")
    fun providesRemoteDataSource(client: RetrofitAPI): HomeDataSource {
        return HomeRemoteDataSourceImpl(client)
    }
}