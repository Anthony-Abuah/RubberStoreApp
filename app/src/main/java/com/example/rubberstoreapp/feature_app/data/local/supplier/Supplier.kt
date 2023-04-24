package com.example.rubberstoreapp.feature_app.data.local.supplier

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Supplier")
data class Supplier(
    @PrimaryKey(autoGenerate = true)
    val supplierId: Int,
    val supplierName: String,
    val supplierContact: String,
    val supplierLocation: String,
    val supplierInfo: String
)
