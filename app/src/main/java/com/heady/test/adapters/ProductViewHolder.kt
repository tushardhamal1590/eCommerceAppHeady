package kotlincodes.com.retrofitwithkotlin.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.heady.test.R
import com.heady.test.model.Product
import com.heady.test.model.Varient

class ProductViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
         RecyclerView.ViewHolder(inflater.inflate(R.layout.list_cell_item_filter, parent, false)) {

    private var mProduct: TextView? = null
    private var mPrice: TextView? = null
    private var mSize: TextView? = null


    init {
        mProduct = itemView.findViewById(R.id.filterText)
        mPrice = itemView.findViewById(R.id.price)
        mSize = itemView.findViewById(R.id.size)
    }

    fun bind(product: Product) {
        mProduct?.text = product.name

    }
}