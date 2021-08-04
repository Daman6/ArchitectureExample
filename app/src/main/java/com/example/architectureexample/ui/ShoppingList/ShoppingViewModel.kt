package com.example.architectureexample.ui.ShoppingList

import androidx.lifecycle.ViewModel
import com.example.architectureexample.data.db.entities.ShoppingItem
import com.example.architectureexample.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val respository:ShoppingRepository
):ViewModel() {

    fun upsert(item :ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        respository.upsert(item)
    }

    fun delete(item :ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        respository.delete(item)
    }

    fun getAllShoppingItem() = respository.getAllShoppingItem()
}