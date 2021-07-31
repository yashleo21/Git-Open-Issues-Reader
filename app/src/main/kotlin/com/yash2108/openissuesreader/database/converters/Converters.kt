package com.yash2108.openissuesreader.database.converters

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.yash2108.openissuesreader.application.MyApplication
import com.yash2108.openissuesreader.database.entity.Home
import javax.inject.Inject
import javax.inject.Singleton

class Converters @Inject constructor(){

    @Inject
    lateinit var moshi : Moshi

    init {
        MyApplication.instance.appComponent.inject(this)
    }

    @TypeConverter
    fun fromUserObject(user: Home.User): String {
        val jsonAdapter = moshi.adapter<Home.User>(Home.User::class.java)

        return jsonAdapter.toJson(user)
    }

    @TypeConverter
    fun toUserObject(input: String): Home.User {
        val jsonAdapter = moshi.adapter(Home.User::class.java)

        return jsonAdapter.fromJson(input)!!
    }
}