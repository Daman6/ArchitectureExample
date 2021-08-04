package com.example.architectureexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architectureexample.data.db.ShoppingDatabase
import com.example.architectureexample.data.db.entities.ShoppingItem
import com.example.architectureexample.data.repositories.ShoppingRepository
import com.example.architectureexample.other.ShoppingItemAdapter
import com.example.architectureexample.ui.ShoppingList.AddDailogListener
import com.example.architectureexample.ui.ShoppingList.AddShoppingDialog
import com.example.architectureexample.ui.ShoppingList.ShoppingViewModel
import com.example.architectureexample.ui.ShoppingList.ShoppingViewModelFactory
import kotlinx.android.synthetic.main.activity_shopping.*

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)


        val viewModel =ViewModelProviders.of(this,factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

       viewModel.getAllShoppingItem()
           .observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            AddShoppingDialog(
                this, object : AddDailogListener {
                    override fun onAddButtonClick(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }).show()
        }
    }
}