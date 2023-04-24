package com.example.rubberstoreapp.feature_app.domain.repository

import com.example.rubberstoreapp.feature_app.data.local.item.Item
import kotlinx.coroutines.flow.Flow

typealias Items = List<Item>

interface ItemRepository {

    fun allItems(): Flow<Items>

    suspend fun addItem(item: Item)

    suspend fun updateItem(item: Item)

    suspend fun deleteItem(item: Item)

    suspend fun removeItem(itemId: Int)

    suspend fun getItem(itemId: Int): Item
}

