package com.example.rubberstoreapp.feature_app.data.local.customer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Customer")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val customerId: Int,
    val customerName: String,
    val customerContact: String,
    val customerLocation: String,
    val customerInfo: String
)
