package com.yash2108.openissuesreader.models

import com.yash2108.openissuesreader.database.entity.HomeDataObject

interface HomeDataSource {

   suspend fun getData(): List<HomeDataObject>

   suspend fun getCommentsData(url: String): List<DetailDataObject>
}