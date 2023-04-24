package com.example.rubberstoreapp.feature_app.data.local.inventory

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rubberstoreapp.feature_app.data.local.inventory_items.InventoryItems
import java.util.Date


@Entity(tableName = "Inventory")
data class Inventory(
    @PrimaryKey(autoGenerate = true)
    val inventoryId: Int,
    val date: Date,
    val items: List<InventoryItems>,
    val totalCost: Double

)
