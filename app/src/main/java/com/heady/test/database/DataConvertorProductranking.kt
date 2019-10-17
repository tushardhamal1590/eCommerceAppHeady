package com.heady.test.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.heady.test.model.ProductRanking
import com.heady.test.model.Varient

class DataConvertorProductranking {

    @TypeConverter
    fun fromProductRankingList(value: List<ProductRanking>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ProductRanking>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toProductRankingList(value: String): List<ProductRanking> {
        val gson = Gson()
        val type = object : TypeToken<List<ProductRanking>>() {}.type
        return gson.fromJson(value, type)
    }
}