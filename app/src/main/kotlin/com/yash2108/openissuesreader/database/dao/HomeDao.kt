package com.yash2108.openissuesreader.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yash2108.openissuesreader.database.Constants
import com.yash2108.openissuesreader.database.entity.HomeDataObject

@Dao
interface HomeDao {

    @Insert
    suspend fun insert(issueObject: HomeDataObject)

    @Insert
    suspend fun insertAllRecords(issues: List<HomeDataObject>)

    @Query("DELETE FROM ${Constants.TABLE_NAME}")
    suspend fun deleteAllRcords()

    @Query("SELECT * FROM ${Constants.TABLE_NAME}")
    fun getAllRecords(): List<HomeDataObject>
}