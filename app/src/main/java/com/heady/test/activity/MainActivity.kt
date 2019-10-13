package com.heady.test.activity

import android.app.ProgressDialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import com.heady.test.R
import com.heady.test.model.Category
import com.heady.test.model.FinalResponse
import com.heady.test.model.Product
import com.heady.test.model.Varient
import kotlincodes.com.retrofitwithkotlin.adapters.ExpandableAdapter
import kotlincodes.com.retrofitwithkotlin.adapters.ProductAdapter
import kotlincodes.com.retrofitwithkotlin.retrofit.ApiClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

var listOfProduct: MutableList<String> = ArrayList()


class MainActivity : AppCompatActivity() {

    lateinit var progerssProgressDialog: ProgressDialog
    lateinit var expandableListView: ExpandableListView
    var adapter: ExpandableListAdapter? = null
    var hashMapProduct: HashMap<String, MutableList<Product>> = HashMap()
    var context: Context? = null


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

    }

    fun bottomsheet(list: List<Varient>) {

        var recyclerView: RecyclerView?
        var listAdapter: ProductAdapter?
        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)


        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        listAdapter = ProductAdapter(list)
        recyclerView?.adapter = listAdapter
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

