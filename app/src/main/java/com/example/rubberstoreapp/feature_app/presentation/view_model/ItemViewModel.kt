package com.example.rubberstoreapp.feature_app.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.item.Item
import com.example.rubberstoreapp.feature_app.domain.repository.ItemRepository
import com.example.rubberstoreapp.feature_app.presentation.view_model.states.ItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val itemRepository: ItemRepository
): ViewModel() {
    var item by mutableStateOf(Item(0, NO_VALUE, NO_VALUE, NO_VALUE))
        private set
    var openDialog by mutableStateOf(false)

    var deleteClicked by mutableStateOf(false)

    val items = itemRepository.allItems()


    fun addItem(item: Item) = viewModelScope.launch {
        itemRepository.addItem(item)
    }

    fun updateItem(item: Item) = viewModelScope.launch {
        itemRepository.updateItem(item)
    }

    fun deleteItem(item: Item) = viewModelScope.launch {
        itemRepository.deleteItem(item)
    }

    fun removeItem(itemId: Int) = viewModelScope.launch {
        itemRepository.removeItem(itemId)
    }

    fun getItem(itemId: Int) = viewModelScope.launch {
        item = itemRepository.getItem(itemId)
    }

    fun updateItemName(itemName: String) {
        item = item.copy(
            itemName = itemName
        )
    }

    fun updateItemManufacturer(itemManufacturer: String) {
        item = item.copy(
            itemManufacturer = itemManufacturer
        )
    }
    fun updateItemDescription(itemDescription: String) {
        item = item.copy(
            itemDescription = itemDescription
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