package com.heady.test.model

import com.google.gson.annotations.SerializedName

class FinalResponse {

    @SerializedName("categories")
    val category: List<Category> = ArrayList()

    @SerializedName("rankings")
    val ranking: List<Ranking> = ArrayList()
}

class Ranking {

    @SerializedName("ranking")
    val ranking: String? = null

    @SerializedName("products")
    val listRankProduct: List<ProductRanking> = ArrayList()

}

class ProductRanking {

    @SerializedName("id")
    val productId: Int? = null

    @SerializedName("view_count")
    val viewCount: Int? = null
}


class Category {

    @SerializedName("id")
    val id: Int? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("products")
    val product: List<Product> = ArrayList()
}

class Product {

    @SerializedName("id")
    val id: Int? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("date_added")
    val date: String? = null


    @SerializedName("variants")
    val varient: List<Varient> = ArrayList()

    @SerializedName("tax")
    val tax: Tax? = null
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