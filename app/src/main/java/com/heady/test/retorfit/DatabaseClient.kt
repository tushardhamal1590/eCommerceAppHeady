package com.heady.test.retorfit

import android.content.Context
import androidx.room.Room
import com.heady.test.database.AppDatabase

class DatabaseClient(val context: Context) {


    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "products-list.db"
    ).build()
}