package kotlincodes.com.retrofitwithkotlin.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.heady.test.R
import com.heady.test.model.Varient

class VarientViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
         RecyclerView.ViewHolder(inflater.inflate(R.layout.list_cell_item, parent, false)) {

    private var mColor: TextView? = null
    private var mPrice: TextView? = null
    private var mSize: TextView? = null


    init {
        mColor = itemView.findViewById(R.id.color)
        mPrice = itemView.findViewById(R.id.price)
        mSize = itemView.findViewById(R.id.size)
    }

    fun bind(varient: Varient) {
        mColor?.text = varient.color
        mPrice?.text = varient.price.toString()
        mSize?.text = varient.size.toString()
    }
}