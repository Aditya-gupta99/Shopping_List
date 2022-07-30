package com.sparklead.shoppinglist.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sparklead.shoppinglist.R
import com.sparklead.shoppinglist.data.db.entities.ShoppingItem
import com.sparklead.shoppinglist.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingListAdapter(
    var item : List<ShoppingItem>,
    private val viewModel :ShoppingViewModel
): RecyclerView.Adapter<ShoppingListAdapter.ShoppingViewHolder>() {

    inner class ShoppingViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item,
            parent,
            false
        )
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = item[position]

        holder.itemView.tvName.text = curShoppingItem.name
        holder.itemView.tvAmount.text = "${curShoppingItem.amount}"

        holder.itemView.ivDelete.setOnClickListener {
            viewModel.delete(curShoppingItem)
        }
        holder.itemView.ivPlus.setOnClickListener {
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }
        holder.itemView.ivMinus.setOnClickListener{
            if(curShoppingItem.amount >0){
                curShoppingItem.amount--
                viewModel.upsert(curShoppingItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }
}