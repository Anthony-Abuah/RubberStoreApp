package com.example.rubberstoreapp.feature_app.data.local.debt

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rubberstoreapp.feature_app.data.local.customer.Customer
import java.util.*

@Entity(tableName = "Debt")
data class Debt(
    @PrimaryKey(autoGenerate = true)
    val debtId: Int,
    val date: Date,
    @Embedded
    val customer: Customer,
    val amount: Double
)