package com.yash2108.openissuesreader.di.modules

import android.content.Context
import com.yash2108.openissuesreader.adapters.HomeAdapter
import com.yash2108.openissuesreader.database.dao.HomeDao
import com.yash2108.openissuesreader.di.modules.DatabaseModule
import com.yash2108.openissuesreader.models.HomeDataSource
import com.yash2108.openissuesreader.models.HomeLocalDataSourceImpl
import com.yash2108.openissuesreader.models.HomeRemoteDataSourceImpl
import com.yash2108.openissuesreader.network.service.RetrofitAPI
import com.yash2108.openissuesreader.repositories.HomeRepository
import com.yash2108.openissuesreader.ui.di.scopes.ActivityScoped
import com.yash2108.openissuesreader.ui.di.scopes.FragmentScoped
import com.yash2108.openissuesreader.ui.di.scopes.ViewModelScoped
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class HomeScreenModule {
    @Provides
    @ActivityScoped
    fun providesHomeRepository(@Named("local") localDataSource: HomeDataSource,
                               @Named("remote") remoteDataSource: HomeDataSource,
                               dao: HomeDao): HomeRepository {
        return HomeRepository(localDataSource, remoteDataSource, dao)
    }

    @Provides
    @Named("local")
    @ActivityScoped
    fun providesLocalDataSource(dao: HomeDao): HomeDataSource {
        return HomeLocalDataSourceImpl(dao)
    }

    @Provides
    @Named("remote")
    @ActivityScoped
    fun providesRemoteDataSource(client: RetrofitAPI): HomeDataSource {
        return HomeRemoteDataSourceImpl(client)
    }

}