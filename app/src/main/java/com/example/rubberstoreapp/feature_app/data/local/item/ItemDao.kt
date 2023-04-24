package com.example.rubberstoreapp.feature_app.data.local.item

import androidx.room.*
import com.example.rubberstoreapp.core.util.Constants.ITEM_TABLE
import com.example.rubberstoreapp.feature_app.domain.repository.Items
import kotlinx.coroutines.flow.Flow


@Dao
interface ItemDao {

    @Query("SELECT * FROM $ITEM_TABLE ORDER BY itemId DESC ")
    fun allItems(): Flow<Items>

    @Query("DELETE FROM $ITEM_TABLE WHERE itemId LIKE :id")
    suspend fun removeItem(id: Int)

    @Query("SELECT * FROM $ITEM_TABLE WHERE itemId LIKE :itemId")
    suspend fun getItem(itemId: Int): Item

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(item: Item)

    @Update
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

}