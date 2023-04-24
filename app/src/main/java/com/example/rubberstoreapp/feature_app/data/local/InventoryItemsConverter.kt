package com.example.rubberstoreapp.feature_app.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.rubberstoreapp.feature_app.data.local.inventory_items.InventoryItems
import com.example.rubberstoreapp.feature_app.data.util.JsonParser
import com.google.gson.reflect.TypeToken
import java.util.*


@ProvidedTypeConverter
class InventoryItemsConverter(
        private val jsonParser: JsonParser
    ) {

        @TypeConverter
        fun fromInventoryItemsJson(json: String): List<InventoryItems>{
            return jsonParser.fromJson<ArrayList<InventoryItems>>(
                json, object : TypeToken<ArrayList<InventoryItems>>(){}.type) ?: emptyList()
        }

        @TypeConverter
        fun toInventoryJson(inventoryItems: List<InventoryItems>): String{
            return jsonParser.toJson(inventoryItems, object : TypeToken<ArrayList<InventoryItems>>(){}.type) ?: "[]"
        }
}
