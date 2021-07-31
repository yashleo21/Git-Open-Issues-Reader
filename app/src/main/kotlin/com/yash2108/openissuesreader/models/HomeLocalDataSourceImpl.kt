package com.yash2108.openissuesreader.models

import com.yash2108.openissuesreader.database.AppDatabase
import com.yash2108.openissuesreader.database.dao.HomeDao
import com.yash2108.openissuesreader.database.entity.Home
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeLocalDataSourceImpl @Inject constructor(val dao: HomeDao): HomeDataSource {

    override suspend fun getData(): ArrayList<Home> {
        return dao.getAllRecords()
    }
}