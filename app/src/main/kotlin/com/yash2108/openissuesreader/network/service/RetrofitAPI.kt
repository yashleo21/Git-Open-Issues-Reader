package com.yash2108.openissuesreader.network.service

import com.yash2108.openissuesreader.database.entity.Home
import com.yash2108.openissuesreader.network.Endpoints
import retrofit2.http.GET

interface RetrofitAPI {

    @GET(Endpoints.OKHTTP_ISSUES)
    suspend fun getIssuesList(): ArrayList<Home>

}