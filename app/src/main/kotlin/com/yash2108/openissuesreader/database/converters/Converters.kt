package com.yash2108.openissuesreader.database.converters

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.yash2108.openissuesreader.application.MyApplication
import com.yash2108.openissuesreader.database.entity.HomeDataObject
import javax.inject.Inject

class Converters @Inject constructor(){

    @Inject
    lateinit var moshi : Moshi

    init {
        MyApplication.instance.appComponent.inject(this)
    }

    @TypeConverter
    fun fromUserObject(user: HomeDataObject.User): String {
        val jsonAdapter = moshi.adapter<HomeDataObject.User>(HomeDataObject.User::class.java)

        return jsonAdapter.toJson(user)
    }

    @TypeConverter
    fun toUserObject(input: String): HomeDataObject.User {
        val jsonAdapter = moshi.adapter(HomeDataObject.User::class.java)

        return jsonAdapter.fromJson(input)!!
    }
}