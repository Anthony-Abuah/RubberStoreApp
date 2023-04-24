package com.example.rubberstoreapp.feature_app.data.repository

import com.example.rubberstoreapp.feature_app.data.local.inventory.Inventory
import com.example.rubberstoreapp.feature_app.data.local.inventory.InventoryDao
import com.example.rubberstoreapp.feature_app.domain.repository.Inventories
import com.example.rubberstoreapp.feature_app.domain.repository.InventoryDateCosts
import com.example.rubberstoreapp.feature_app.domain.repository.InventoryRepository
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class InventoryRepositoryImpl @Inject constructor(
    private val inventoryDao: InventoryDao
): InventoryRepository {
    override fun allInventories(): Flow<Inventories> {
        return inventoryDao.allInventories()
    }

    override fun getAllTimeInventoryDateCosts(): Flow<InventoryDateCosts> {
        return inventoryDao.getAllTimeInventoryDateCosts()
    }

    override fun getDurationalInventoryDateCosts(
        firstDate: Date,
        lastDate: Date
    ): Flow<InventoryDateCosts> {
        return inventoryDao.getDurationalInventoryDateCosts(firstDate, lastDate)
    }

    override fun durationalInventories(firstDate: Date, lastDate: Date): Flow<Inventories> {
        return inventoryDao.durationalInventories(firstDate, lastDate)
    }

    override suspend fun addInventory(inventory: Inventory) {
        return inventoryDao.addInventory(inventory)
    }

    override suspend fun updateInventory(inventory: Inventory) {
        return inventoryDao.updateInventory(inventory)
    }

    override suspend fun deleteInventory(inventory: Inventory) {
        return inventoryDao.deleteInventory(inventory)    }

    override suspend fun removeInventory(inventoryId: Int) {
        return inventoryDao.removeInventory(inventoryId)    }

    override suspend fun getAllTimeTotalCost(): Double? {
        return inventoryDao.getAllTimeTotalCost()
    }

    override suspend fun getDurationalTotalCost(firstDate: Date, lastDate: Date): Double? {
        return inventoryDao.getDurationalTotalCost(firstDate, lastDate)
    }

}