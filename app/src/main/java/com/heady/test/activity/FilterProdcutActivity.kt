package com.heady.test.activity

import android.app.ProgressDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ImageView
import com.heady.test.R
import com.heady.test.model.*
import kotlincodes.com.retrofitwithkotlin.adapters.FilterAdapter
import kotlincodes.com.retrofitwithkotlin.adapters.ProdcutAdapter
import kotlincodes.com.retrofitwithkotlin.adapters.VarientAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList


class FilterProdcutActivity : AppCompatActivity() {

    var context: Context? = null

    var keyWithProductRanking: HashMap<String?, MutableList<ProductRanking>> = HashMap()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filter_product_activity)
        setSupportActionBar(toolbar)

        context = this


        var mutableList: MutableList<String?> =  keyWithProductRanking.keys.toMutableList()

        var recyclerView: RecyclerView?
        var filterAdapter: ProdcutAdapter?


        recyclerView = findViewById(R.id.filterProductRecyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
//        filterAdapter = ProdcutAdapter(mutableList)
//        recyclerView?.adapter = filterAdapter


    }




}

