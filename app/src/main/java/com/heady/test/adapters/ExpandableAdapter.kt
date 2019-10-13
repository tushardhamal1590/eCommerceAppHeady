package kotlincodes.com.retrofitwithkotlin.adapters

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.heady.test.R
import com.heady.test.model.Product
import com.heady.test.model.Tax
import com.heady.test.model.Varient


class ExpandableAdapter(val context: Context, val titleList: List<String>, var dataList:  HashMap<String,MutableList<Product>>) : BaseExpandableListAdapter() {


    var listener: ((List<Varient>)->Unit)? = null

    override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
        return this.dataList[this.titleList[listPosition]]!![expandedListPosition]
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }


    override fun getChildView(listPosition: Int, expandedListPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val product = getChild(listPosition, expandedListPosition) as Product
        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_item, null)
        }
        val expandedListTextView = convertView!!.findViewById<TextView>(R.id.expandedListItem)
        val taxName = convertView!!.findViewById<TextView>(R.id.taxName)
        val taxVal = convertView!!.findViewById<TextView>(R.id.taxVal)
        val variants = convertView!!.findViewById<TextView>(R.id.variants)

        val cardView = convertView!!.findViewById<CardView>(R.id.card_view)
        expandedListTextView.text = product.name
        val tax : Tax? = product.tax
        taxName.text = tax!!.name
        taxVal.text = product.id.toString()
        variants.text = product.varient.size.toString()

        cardView.setOnClickListener{
            listener?.invoke(product.varient) }

        return convertView

    }
    override fun getChildrenCount(listPosition: Int): Int {
        return this.dataList[this.titleList[listPosition]]!!.size
    }


    override fun getGroup(listPosition: Int): Any {
        return this.titleList[listPosition]
    }

    override fun getGroupCount(): Int {
        return this.titleList.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(listPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val listTitle = getGroup(listPosition) as String
        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_group, null)
        }
        val listTitleTextView = convertView!!.findViewById<TextView>(R.id.listTitle)
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = listTitle
        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }

}