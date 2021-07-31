package com.yash2108.openissuesreader.models
import com.yash2108.openissuesreader.database.entity.HomeDataObject
import com.yash2108.openissuesreader.network.service.RetrofitAPI
import org.json.JSONException
import retrofit2.Response
import java.net.SocketTimeoutException
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(val client: RetrofitAPI): HomeDataSource {

    override suspend fun getData(): List<HomeDataObject> {
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