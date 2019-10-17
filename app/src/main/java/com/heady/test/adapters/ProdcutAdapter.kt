package kotlincodes.com.retrofitwithkotlin.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.heady.test.model.Product
import com.heady.test.model.Varient

class ProdcutAdapter(private val list: List<Product>) : RecyclerView.Adapter<ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(inflater, parent)

    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val varient: Product = list[position]
        holder.bind(varient)
    }

    override fun getItemCount(): Int = list.size

}