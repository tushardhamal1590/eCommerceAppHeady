package kotlincodes.com.retrofitwithkotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.heady.test.model.Varient

class ProductAdapter(private val list: List<Varient>) : RecyclerView.Adapter<ProductVarientViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVarientViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return ProductVarientViewHolder(inflater, parent)

    }

    override fun onBindViewHolder(holder: ProductVarientViewHolder, position: Int) {
        val varient: Varient = list[position]
        holder.bind(varient)
    }

    override fun getItemCount(): Int = list.size

}