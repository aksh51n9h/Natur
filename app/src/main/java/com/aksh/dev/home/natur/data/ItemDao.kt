package com.aksh.dev.home.natur.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(item: Item)

    @Delete
    fun removeItem(item: Item)

    @Update
    fun updateItem(item: Item)

    @get:Query("SELECT * from items_table ORDER BY id DESC")
    val allItems: LiveData<List<Item>>

    @get:Query("SELECT * from items_table ORDER BY id DESC LIMIT 10")
    val newItems: LiveData<List<Item>>


    @Query("SELECT * FROM items_table WHERE name=:name LIMIT 1")
    fun searchItem(name: String): Boolean
}