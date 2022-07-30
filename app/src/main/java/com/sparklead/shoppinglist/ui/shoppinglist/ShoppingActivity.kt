package com.sparklead.shoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sparklead.shoppinglist.R
import com.sparklead.shoppinglist.data.db.ShoppingDatabase
import com.sparklead.shoppinglist.data.db.entities.ShoppingItem
import com.sparklead.shoppinglist.data.repositories.ShoppingRepository
import com.sparklead.shoppinglist.ui.adapters.ShoppingListAdapter
import kotlinx.android.synthetic.main.activity_shopping.*

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory =ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProviders.of(this, factory)[ShoppingViewModel::class.java]

        val adapter = ShoppingListAdapter(listOf(),viewModel)

        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer{
            adapter.item = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            AddShoppingItemDialog(
                this,
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }
            ).show()
        }

    }
}