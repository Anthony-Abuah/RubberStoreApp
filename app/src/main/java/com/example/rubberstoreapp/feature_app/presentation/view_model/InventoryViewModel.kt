package com.example.rubberstoreapp.feature_app.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.inventory.Inventory
import com.example.rubberstoreapp.feature_app.data.local.inventory_items.InventoryItems
import com.example.rubberstoreapp.feature_app.domain.repository.Inventories
import com.example.rubberstoreapp.feature_app.domain.repository.InventoryDateCosts
import com.example.rubberstoreapp.feature_app.domain.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val inventoryRepository: InventoryRepository
): ViewModel() {
    var inventory by mutableStateOf(Inventory(0, Date(), emptyList(), 0.0))
        private set

    var inventoryItem by mutableStateOf(InventoryItems(NO_VALUE, 0.0, 0.0))
        private set


    var allTimeInventoryDateCosts by mutableStateOf(emptyFlow<InventoryDateCosts>())
        private set

    var durationalInventoryDateCosts by mutableStateOf(emptyFlow<InventoryDateCosts>())
        private set

    var durationalInventories by mutableStateOf(emptyFlow<Inventories>())
        private set

    var allTimeTotalCost by mutableStateOf(0.0)
        private set

    var durationalTotalCost by mutableStateOf(0.0)
        private set


    private var _inventoryItems: MutableList<InventoryItems> = mutableListOf()
    val inventoryItems = _inventoryItems


    private val _eggsReducedByEggType: MutableLiveData<Int?> = MutableLiveData(0)
    val eggsReducedByEggType: LiveData<Int?> = _eggsReducedByEggType

    var openDialog by mutableStateOf(false)

    var deleteClicked by mutableStateOf(false)

    val allInventories = inventoryRepository.allInventories()


    fun addInventory(inventory: Inventory) = viewModelScope.launch {
        inventoryRepository.addInventory(inventory)
    }

    fun updateInventory(inventory: Inventory) = viewModelScope.launch {
        inventoryRepository.updateInventory(inventory)
    }

    fun deleteInventory(inventory: Inventory) = viewModelScope.launch {
        inventoryRepository.deleteInventory(inventory)
    }

    fun removeInventory(inventoryId: Int) = viewModelScope.launch {
        inventoryRepository.removeInventory(inventoryId)
    }

    fun getAllTimeTotalCost() = viewModelScope.launch {
        allTimeTotalCost = inventoryRepository.getAllTimeTotalCost() ?: 0.0
    }

    fun getDurationalTotalCost(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        durationalTotalCost = inventoryRepository.getDurationalTotalCost(firstDate, lastDate) ?: 0.0
    }

    fun getAllTimeInventoryDateCosts() = viewModelScope.launch {
        allTimeInventoryDateCosts = inventoryRepository.getAllTimeInventoryDateCosts()
    }

    fun getDurationalInventoryDateCosts(firstDate: Date, lastDate: Date) = viewModelScope.launch {
        durationalInventoryDateCosts =
            inventoryRepository.getDurationalInventoryDateCosts(firstDate, lastDate)
    }

    fun updateInventoryDate(date: Date) {
        inventory = inventory.copy(
            date = date
        )
    }

    fun updateInventoryTotalCost(totalCost: Double) {
        inventory = inventory.copy(
            totalCost = totalCost
        )
    }

    fun updateInventoryItemsList() {
        inventory = inventory.copy(
            items = _inventoryItems
        )
    }

    fun addInventoryItemToListOfInventoryItems(inventoryItem: InventoryItems) {
        _inventoryItems.add(inventoryItem)
    }

    fun addToInventoryItem(name: String, quantity: Double, cost: Double) {
        inventoryItem = inventoryItem.copy(
            itemName = name,
            quantity = quantity,
            cost = cost
        )
    }

    fun updateInventoryItemName(name: String) {
        inventoryItem = inventoryItem.copy(
            itemName = name
        )
    }

    fun updateInventoryItemQuantity(quantity: Double) {
        inventoryItem = inventoryItem.copy(
            quantity = quantity
        )
    }

    fun updateInventoryItemCost(cost: Double) {
        inventoryItem = inventoryItem.copy(
            cost = cost
        )
    }

    fun deleteClicked() {
        deleteClicked = true
    }

    fun cancelClicked() {
        deleteClicked = false
    }

    fun openDialog() {
        openDialog = true
    }

    fun closeDialog() {
        openDialog = false
    }

}