package com.heady.test.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class FinalResponse {

    @SerializedName("categories")
    val category: List<Category> = ArrayList()

    @SerializedName("rankings")
    val ranking: List<Ranking> = ArrayList()
}

@Entity(tableName = "product_ranking")
class Ranking {

    @SerializedName("ranking")
    var ranking: String? = null

    @PrimaryKey(autoGenerate = true)
    var id:Int? = null

    @SerializedName("products")
    var listRankProduct: List<ProductRanking> = ArrayList()

}

class ProductRanking {

    @SerializedName("id")
    var productId: Int? = null

    @SerializedName("view_count")
    var viewCount: Int? = null
}


class Category {

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("products")
    var product: List<Product> = ArrayList()
}

@Entity(tableName = "product")
class Product {

    @PrimaryKey
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("name")
    var name: String? = null

//    @SerializedName("date_added")
//    val date: String? = null


    @SerializedName("variants")
    var varient: List<Varient> = ArrayList()

    @SerializedName("tax")
    var tax: Tax? = null
}


class Varient {

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("color")
    var color: String? = null

    @SerializedName("size")
    var size: Int? = null

    @SerializedName("price")
    var price: Int? = null

}

class Tax {

    @SerializedName("value")
    val id: Double? = null

    @SerializedName("name")
    val name: String? = null
}