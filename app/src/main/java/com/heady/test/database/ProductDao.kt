package com.heady.test.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heady.test.model.Product
import com.heady.test.model.ProductRanking

@Dao
interface ProductDao {

    @Query("SELECT * FROM PRODUCT")
    fun getAllProducts ():List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg product: Product)


    @Query("SELECT * FROM PRODUCT WHERE id IN (:ids)")
    fun queryProducts(ids: List<ProductRanking>): List<Product>

}