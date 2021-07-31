package com.yash2108.openissuesreader.models

import com.yash2108.openissuesreader.database.dao.HomeDao
import com.yash2108.openissuesreader.database.entity.HomeDataObject
import javax.inject.Inject

class HomeLocalDataSourceImpl @Inject constructor(val dao: HomeDao): HomeDataSource {

    override suspend fun getData(): List<HomeDataObject> {
        return dao.getAllRecords()
    }

    override suspend fun getCommentsData(url: String): List<DetailDataObject> {
        //Not implemented
        return emptyList()
    }
}