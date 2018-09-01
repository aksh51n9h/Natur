package com.aksh.dev.home.natur.data

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class ItemRepository(application: Application) {
    private val database: Database = Database.getDatabase(application)
    private val itemDao: ItemDao = database.itemDao()

    val allItems: LiveData<List<Item>> = itemDao.allItems
    val newItems: LiveData<List<Item>> = itemDao.newItems

    fun addItem(item: Item) {
        InsertItem(itemDao).execute(item)
    }

    fun removeItem(item: Item) {
        RemoveItem(itemDao).execute(item)
    }

    fun updateItem(item: Item) {
        UpdateItem(itemDao).execute(item)
    }

    fun searchAndAddItem(item: Item) {
        val contain = SearchItem(itemDao).execute(item).get()

        if (!contain)
            addItem(item)
        else
            updateItem(item)
    }

    class InsertItem(private val itemDao: ItemDao) : AsyncTask<Item, Unit, Unit>() {
        override fun doInBackground(vararg items: Item) {
            itemDao.addItem(items[0])
        }
    }

    class RemoveItem(private val itemDao: ItemDao) : AsyncTask<Item, Unit, Unit>() {
        override fun doInBackground(vararg items: Item) {
            itemDao.removeItem(items[0])
        }
    }

    class UpdateItem(private val itemDao: ItemDao) : AsyncTask<Item, Unit, Unit>() {
        override fun doInBackground(vararg items: Item) {
            itemDao.updateItem(items[0])
        }
    }

    class SearchItem(private val itemDao: ItemDao) : AsyncTask<Item, Unit, Boolean>() {
        override fun doInBackground(vararg items: Item): Boolean = itemDao.searchItem(items[0].name)
    }
}