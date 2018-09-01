package com.aksh.dev.home.natur.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "items_table")
data class Item(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        var name: String,
        var category: String,
        var description: String,
        var photoUrl: String
) {
    @Ignore
    constructor() : this(0, "", "", "", "")

    fun toArray(): Array<String> = arrayOf("$id", name, category, description, photoUrl)
}