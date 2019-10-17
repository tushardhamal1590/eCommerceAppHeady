package kotlincodes.com.retrofitwithkotlin.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.heady.test.model.Product
import com.heady.test.model.ProductRanking

class FilterAdapter(
    private val list: MutableList<String?>, val keyWithProductRanking: HashMap<String?, MutableList<ProductRanking>>,
    var prodList: List<Product>
) : RecyclerView.Adapter<FilterViewHolder>() {

    var onItemClick: ((List<Product>) -> Unit)? = null


    var mutableList: MutableList<Product> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return FilterViewHolder(inflater, parent)

    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val varient: String? = list[position]
        var productRanking = keyWithProductRanking.get(varient)

        if (productRanking != null) {
            for (prodRank in productRanking) {
                for (prod in prodList) {
                    if (prodRank.productId == prod.id)
                        mutableList.add(prod)
                }
            }
        }


        varient?.let { holder.bind(it, mutableList) }


    }

    override fun getItemCount(): Int = list.size

}