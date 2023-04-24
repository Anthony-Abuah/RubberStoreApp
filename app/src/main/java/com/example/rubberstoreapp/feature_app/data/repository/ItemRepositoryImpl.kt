package com.example.rubberstoreapp.feature_app.data.repository

import com.example.rubberstoreapp.feature_app.data.local.item.Item
import com.example.rubberstoreapp.feature_app.data.local.item.ItemDao
import com.example.rubberstoreapp.feature_app.domain.repository.ItemRepository
import com.example.rubberstoreapp.feature_app.domain.repository.Items
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val itemDao: ItemDao
): ItemRepository {
    override fun allItems(): Flow<Items> {
        return itemDao.allItems()
    }

    override suspend fun addItem(item: Item) {
        return itemDao.addItem(item)    }

    override suspend fun updateItem(item: Item) {
        return itemDao.updateItem(item)    }

    override suspend fun deleteItem(item: Item) {
        return itemDao.deleteItem(item)    }

    override suspend fun removeItem(itemId: Int) {
        return itemDao.removeItem(itemId)    }

    override suspend fun getItem(itemId: Int): Item {
        return itemDao.getItem(itemId)
    }

}