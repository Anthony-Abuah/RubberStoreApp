package com.example.rubberstoreapp.feature_app.presentation.view_model.states

import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.bank.Bank
import com.example.rubberstoreapp.feature_app.data.local.inventory_items.InventoryItems
import java.util.*

data class InventoryState (
    val inventoryId: Int = 0,
    val date: Date = Date(),
    val items: List<InventoryItems> = emptyList(),
    val totalCost: Double = 0.0
)