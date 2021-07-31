package com.yash2108.openissuesreader.models

import com.yash2108.openissuesreader.database.entity.Home
import com.yash2108.openissuesreader.network.service.RetrofitAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONException
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException

class HomeRemoteDataSourceImpl(val client: RetrofitAPI): HomeDataSource {

    override suspend fun getData(): ArrayList<Home> {
        return client.getIssuesList()
    }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Result<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body()!!)
            } else {
                Result.error(JSONException(defaultErrorMessage))
            }
        } catch (e: Throwable) {
            Result.error(SocketTimeoutException(defaultErrorMessage))
        }
    }
}