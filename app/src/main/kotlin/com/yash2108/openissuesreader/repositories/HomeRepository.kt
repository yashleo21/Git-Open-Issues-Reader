package com.yash2108.openissuesreader.repositories

import com.yash2108.openissuesreader.database.dao.HomeDao
import com.yash2108.openissuesreader.database.entity.Home
import com.yash2108.openissuesreader.models.HomeDataSource
import com.yash2108.openissuesreader.models.ResultUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepository @Inject constructor(private val localDataSource: HomeDataSource,
                                         private val remoteDataSource: HomeDataSource,
                                         private val dao: HomeDao) {


    suspend fun getIssuesList(): Flow<ResultUI<ArrayList<Home>>> {
        return flow {
            emit(fetchLocalData())
            emit(ResultUI.loading())
            val result = remoteDataSource.getData()

            //Write to DB on success
            if (result?.isNotEmpty()) {
                dao.deleteAllRcords()
                dao.insertAllRecords(result)
            }

            emit(ResultUI.success(result))
        }
    }

    private suspend fun fetchLocalData(): ResultUI<ArrayList<Home>> = localDataSource.getData().let {
        return ResultUI.success(it)
    }
}