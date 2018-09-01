package com.aksh.dev.home.natur

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.aksh.dev.home.natur.data.Item
import com.aksh.dev.home.natur.data.ItemRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val itemRepository: ItemRepository = ItemRepository(application)

    var gridLayout: Boolean = true

    val allItems: LiveData<List<Item>> = itemRepository.allItems

    fun getData() {
        val reference = FirebaseDatabase.getInstance().getReference("plants")
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val plants = dataSnapshot.children

                for (plant in plants) {
                    val mPlant = plant.getValue(Item::class.java)

                    if (mPlant != null) {
                        itemRepository.searchAndAddItem(mPlant)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        )
    }
}