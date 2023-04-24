package com.example.rubberstoreapp.feature_app.data.local.inventory

import androidx.room.*
import com.example.rubberstoreapp.core.util.Constants.INVENTORY_TABLE
import com.example.rubberstoreapp.feature_app.domain.repository.Inventories
import com.example.rubberstoreapp.feature_app.domain.repository.InventoryDateCosts
import kotlinx.coroutines.flow.Flow
import java.util.Date


@Dao
interface InventoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addInventory(inventory: Inventory)

    @Query("SELECT * FROM $INVENTORY_TABLE ORDER BY date DESC")
    fun allInventories(): Flow<Inventories>

    @Query("SELECT * FROM $INVENTORY_TABLE WHERE date BETWEEN :firstDate AND :lastDate ORDER BY date DESC")
    fun durationalInventories(firstDate: Date, lastDate: Date): Flow<Inventories>

    @Update
    suspend fun updateInventory(inventory: Inventory)

    @Delete
    suspend fun deleteInventory(inventory: Inventory)

    @Query("DELETE FROM $INVENTORY_TABLE WHERE inventoryId LIKE :inventoryId")
    suspend fun removeInventory(inventoryId: Int)

    @Query("SELECT SUM(totalCost) FROM $INVENTORY_TABLE")
    suspend fun getAllTimeTotalCost(): Double?

    @Query("SELECT SUM(totalCost) FROM $INVENTORY_TABLE WHERE date BETWEEN :firstDate AND :lastDate")
    suspend fun getDurationalTotalCost(firstDate: Date, lastDate: Date): Double?


    @Query("SELECT date AS date, SUM(totalCost) AS amount FROM $INVENTORY_TABLE GROUP BY date")
    fun getAllTimeInventoryDateCosts(): Flow<InventoryDateCosts>

    @Query("SELECT date AS date, SUM(totalCost) AS amount FROM $INVENTORY_TABLE WHERE date BETWEEN :firstDate AND :lastDate GROUP BY date")
    fun getDurationalInventoryDateCosts(firstDate: Date, lastDate: Date): Flow<InventoryDateCosts>


}