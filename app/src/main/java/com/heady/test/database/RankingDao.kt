package com.heady.test.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heady.test.model.Ranking

@Dao
interface RankingDao {

    @Query("SELECT * FROM PRODUCT_RANKING")
    fun getAllProductsRanking ():List<Ranking>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllProductRanking(vararg productRanking: Ranking)
}