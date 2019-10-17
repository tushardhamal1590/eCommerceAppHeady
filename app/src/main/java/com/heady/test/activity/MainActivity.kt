package com.heady.test.activity

import android.app.ProgressDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ImageView
import com.heady.test.R
import com.heady.test.model.*
import com.heady.test.retorfit.DatabaseClient
import kotlincodes.com.retrofitwithkotlin.adapters.ExpandableAdapter
import kotlincodes.com.retrofitwithkotlin.adapters.FilterAdapter
import kotlincodes.com.retrofitwithkotlin.adapters.VarientAdapter
import kotlincodes.com.retrofitwithkotlin.retrofit.ApiClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    lateinit var progerssProgressDialog: ProgressDialog
    lateinit var expandableListView: ExpandableListView
    var adapter: ExpandableListAdapter? = null
    var hashMapProduct: HashMap<String, MutableList<Product>> = HashMap()
    var context: Context? = null

    var productrankingListOnlyProducts: MutableList<ProductRanking> = ArrayList()
    var listOfProduct: MutableList<String> = ArrayList()
    var keyWithProductRanking: HashMap<String?, MutableList<ProductRanking>> = HashMap()
    var imageView: ImageView? = null
    var mutableListKey: MutableList<String?> = ArrayList()
    var productListFromDB: List<Product?> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        context = this


        expandableListView = findViewById(R.id.expandableListView)
        progerssProgressDialog = ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()


        listOfProduct()


        imageView = findViewById(R.id.filter)

        imageView?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                applyFilterList()
            }
        })

    }

    fun bottomsheet(list: List<Varient>) {

        var recyclerView: RecyclerView?
        var listAdapter: VarientAdapter?
        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)


        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.HORIZONTAL,
            false
        )

        listAdapter = VarientAdapter(list)
        recyclerView?.adapter = listAdapter
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.show()
    }


    fun applyFilterList() {


        var recyclerView: RecyclerView?
        var filterAdapter: FilterAdapter?
        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )

        filterAdapter = FilterAdapter(mutableListKey, keyWithProductRanking, productListFromDB as List<Product>)
        recyclerView?.adapter = filterAdapter

        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.show()
    }


    private fun listOfProduct() {
        val call: Call<FinalResponse> = ApiClient.getClient.getFinalResponse()

        call.enqueue(object : Callback<FinalResponse> {
            override fun onResponse(call: Call<FinalResponse>?, response: Response<FinalResponse>?) {

                progerssProgressDialog.dismiss()

                var finalResponse: FinalResponse = response!!.body()!!

                getAllCategoryName(finalResponse.category)

                updateUI()

                GlobalScope.launch {

                    //Insert all Products
                    finalResponse.category?.forEach {
                        it.product.forEach {
                            context?.let { it1 -> DatabaseClient(it1).db.getAllProduct().insertAll(it) }
                        }
                    }


                    //Insert All Product  Ranking
                    finalResponse.ranking.forEach {
                        context?.let { it1 ->
                            DatabaseClient(it1).db.getAllProductRanking().insertAllProductRanking(it)
                        }
                    }

                    var datarnking: List<Ranking>? =
                        context?.let { it1 -> DatabaseClient(it1).db.getAllProductRanking().getAllProductsRanking() }
                    for (da in datarnking!!) {
                        keyWithProductRanking.put(da.ranking, da.listRankProduct as MutableList<ProductRanking>)
                    }

                    productListFromDB = context?.let { DatabaseClient(it).db.getAllProduct().getAllProducts() }!!

                    mutableListKey = keyWithProductRanking.keys.toMutableList()

                }
            }

            override fun onFailure(call: Call<FinalResponse>?, t: Throwable?) {
                progerssProgressDialog.dismiss()
            }

        })
    }


    fun getAllCategoryName(category: List<Category>) {


        for (name in category) {

            var productList: MutableList<Product> = ArrayList()
            name.name?.let { listOfProduct.add(it) }

            for (pName in name.product) {
                pName.name?.let { productList.add(pName) }
            }
            name.name?.let { hashMapProduct.put(it, productList) }
        }
    }


    fun updateUI() {
        if (expandableListView != null) {
            adapter = context?.let { ExpandableAdapter(it, listOfProduct, hashMapProduct) }
            (adapter as ExpandableAdapter?)?.listener = {
                bottomsheet(it)
            }

            expandableListView!!.setAdapter(adapter)
        }
    }


}

