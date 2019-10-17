package com.heady.test.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.heady.test.model.Tax
import com.heady.test.model.Varient

class DataConvertor {

    @TypeConverter
    fun fromVarientList(value: List<Varient>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Varient>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toVarientList(value: String): List<Varient> {
        val gson = Gson()
        val type = object : TypeToken<List<Varient>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromTaxObject(value: Tax):String{

        val gson = Gson()
        val type = object : TypeToken<Tax>() {}.type
        return gson.toJson(value, type)

    }

    @TypeConverter
    fun toTaxObject(value: String): Tax {
        val gson = Gson()
        val type = object : TypeToken<Tax>() {}.type
        return gson.fromJson(value, type)
    }
}