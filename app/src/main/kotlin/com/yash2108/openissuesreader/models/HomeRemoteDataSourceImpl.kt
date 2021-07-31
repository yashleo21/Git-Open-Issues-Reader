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

    override suspend fun getCommentsData(url: String): List<DetailDataObject> {
        return client.getComments(url)
    }

}