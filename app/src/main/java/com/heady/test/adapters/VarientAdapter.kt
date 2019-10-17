package kotlincodes.com.retrofitwithkotlin.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.heady.test.model.Varient

class VarientAdapter(private val list: List<Varient>) : RecyclerView.Adapter<VarientViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VarientViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return VarientViewHolder(inflater, parent)

    }

    override fun onBindViewHolder(holder: VarientViewHolder, position: Int) {
        val varient: Varient = list[position]
        holder.bind(varient)
    }

    override fun getItemCount(): Int = list.size

}