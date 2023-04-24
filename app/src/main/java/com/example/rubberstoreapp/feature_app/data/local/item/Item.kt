package com.example.rubberstoreapp.feature_app.data.local.item

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val itemId: Int,
    val itemName: String,
    val itemManufacturer: String,
    val itemDescription: String
)
