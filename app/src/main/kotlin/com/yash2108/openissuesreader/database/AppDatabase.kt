package com.yash2108.openissuesreader.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yash2108.openissuesreader.database.converters.Converters
import com.yash2108.openissuesreader.database.dao.HomeDao
import com.yash2108.openissuesreader.database.entity.HomeDataObject
import com.yash2108.openissuesreader.models.DetailDataObject


@Database(entities = arrayOf(HomeDataObject::class), version = Constants.VERSION, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun homeDao(): HomeDao
}