package com.sparklead.shoppinglist.ui.shoppinglist

import com.sparklead.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item : ShoppingItem)
}