package com.aksh.dev.home.natur.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.aksh.dev.home.natur.data.Item
import com.aksh.dev.home.natur.data.ItemRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val itemRepository = ItemRepository(application)
    val newItems = itemRepository.newItems

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