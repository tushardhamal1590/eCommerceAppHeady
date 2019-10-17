package com.heady.test.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.heady.test.model.Product
import com.heady.test.model.Ranking

@Database(entities = arrayOf(Product::class,Ranking::class),version = 1)
@TypeConverters(DataConvertor::class,DataConvertorProductranking::class)
abstract class AppDatabase :RoomDatabase(){

    abstract fun getAllProduct ():ProductDao
    abstract fun getAllProductRanking ():RankingDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, "products-list.db")
            .build()
    }
}