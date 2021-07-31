package com.yash2108.openissuesreader.models

import com.yash2108.openissuesreader.database.entity.Home
import kotlinx.coroutines.flow.Flow

interface HomeDataSource {

   suspend fun getData(): ArrayList<Home>
}