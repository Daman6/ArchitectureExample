package com.example.architectureexample.ui.ShoppingList

import com.example.architectureexample.data.db.entities.ShoppingItem

interface AddDailogListener {
    fun onAddButtonClick(item : ShoppingItem)
}