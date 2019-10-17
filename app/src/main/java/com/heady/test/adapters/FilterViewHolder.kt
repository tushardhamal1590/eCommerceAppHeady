package kotlincodes.com.retrofitwithkotlin.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.heady.test.R
import com.heady.test.model.Product
import com.heady.test.model.Varient
import java.text.FieldPosition

class FilterViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
         RecyclerView.ViewHolder(inflater.inflate(R.layout.list_cell_item_filter, parent, false)) {

    private var mFilter: TextView? = null
    lateinit var  list: MutableList<Product>

    var onItemClick: ((List<Product>) -> Unit)? = null

    init {
        itemView.setOnClickListener {
            onItemClick?.invoke(list)
        }
    }


    init {
        mFilter = itemView.findViewById(R.id.filterText)
    }

    fun bind(varient: String,list: MutableList<Product>) {
        mFilter?.text = varient
        this.list = list

    }


}