package com.yash2108.openissuesreader.repositories

import android.util.Log
import com.yash2108.openissuesreader.database.dao.HomeDao
import com.yash2108.openissuesreader.database.entity.HomeDataObject
import com.yash2108.openissuesreader.models.DetailDataObject
import com.yash2108.openissuesreader.models.HomeDataSource
import com.yash2108.openissuesreader.models.ResultUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepository constructor(private val localDataSource: HomeDataSource,
                                         private val remoteDataSource: HomeDataSource,
                                         private val dao: HomeDao) {

    private val TAG = HomeRepository::class.java.simpleName

    suspend fun getIssuesList(): Flow<ResultUI<List<HomeDataObject>>> {
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

    suspend fun getComments(url: String): Flow<ResultUI<List<DetailDataObject>>> {
        return flow {
            emit(ResultUI.loading())
            val result = remoteDataSource.getCommentsData(url)

            emit(ResultUI.success(result))
        }
    }

    private suspend fun fetchLocalData(): ResultUI<List<HomeDataObject>> = localDataSource.getData().let {
        Log.d(TAG, "Found data: ${it.size}")
        return ResultUI.success(it)
    }
}