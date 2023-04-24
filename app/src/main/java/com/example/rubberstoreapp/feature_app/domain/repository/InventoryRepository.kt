package com.example.rubberstoreapp.feature_app.domain.repository

import com.example.rubberstoreapp.feature_app.data.local.room_types.DateAmount
import com.example.rubberstoreapp.feature_app.data.local.inventory.Inventory
import kotlinx.coroutines.flow.Flow
import java.util.Date

typealias Inventories = List<Inventory>
typealias InventoryDateCosts = List<DateAmount>

interface InventoryRepository {

    fun allInventories(): Flow<Inventories>

    fun getAllTimeInventoryDateCosts(): Flow<InventoryDateCosts>

    fun getDurationalInventoryDateCosts(firstDate: Date, lastDate: Date): Flow<InventoryDateCosts>

    fun durationalInventories(firstDate: Date, lastDate: Date): Flow<Inventories>

    suspend fun addInventory(inventory: Inventory)

    suspend fun updateInventory(inventory: Inventory)

    suspend fun deleteInventory(inventory: Inventory)

    suspend fun removeInventory(inventoryId: Int)

    suspend fun getAllTimeTotalCost(): Double?

    suspend fun getDurationalTotalCost(firstDate: Date, lastDate: Date): Double?

}

