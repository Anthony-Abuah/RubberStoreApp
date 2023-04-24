package com.example.rubberstoreapp.feature_app.data.local.expenses

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Expense")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val expenseId: Int,
    val date: Date,
    val expenseName: String,
    val expenseAmount: Double
)
