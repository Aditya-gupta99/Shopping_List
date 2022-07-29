package com.sparklead.shoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.sparklead.shoppinglist.R
import com.sparklead.shoppinglist.data.db.ShoppingDatabase
import com.sparklead.shoppinglist.data.repositories.ShoppingRepository

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory =ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProviders.of(this, factory )[ShoppingViewModel::class.java]

    }
}