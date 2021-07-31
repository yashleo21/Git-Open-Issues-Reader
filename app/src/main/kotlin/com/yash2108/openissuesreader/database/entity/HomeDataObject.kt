package com.yash2108.openissuesreader.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.yash2108.openissuesreader.database.Constants
import retrofit2.http.Field

@Entity(tableName = Constants.TABLE_NAME)
@JsonClass(generateAdapter = true)
data class HomeDataObject(
    @PrimaryKey
    @field:Json(name = "id")
    var id: String,
    @field:Json(name = "body")
    var body: String?,
    @field:Json(name = "title")
    var title: String?,
    @field:Json(name = "updated_at")
    var updated_at: String?,
    @field:Json(name = "user")
    var user: User?
) {
    data class User(
        @ColumnInfo(name = "username")
        @field:Json(name = "login")
        var login: String?,
        @ColumnInfo(name = "avatar")
        @field:Json(name = "avatar_url")
        var avatar_url: String?,)
}