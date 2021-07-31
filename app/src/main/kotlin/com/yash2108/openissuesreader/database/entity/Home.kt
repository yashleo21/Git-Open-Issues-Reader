package com.yash2108.openissuesreader.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yash2108.openissuesreader.database.Constants

@Entity(tableName = Constants.TABLE_NAME)
data class Home(
    @PrimaryKey
    var id: String,
    var title: String?,
    var updated_at: String?,
    var user: User?
) {
    data class User(
        @ColumnInfo(name = "username")
        var login: String?,
        @ColumnInfo(name = "avatar")
        var avatar_url: String?,)
}