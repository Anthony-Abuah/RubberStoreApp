package com.example.rubberstoreapp.feature_app.data.local.savings

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rubberstoreapp.feature_app.data.local.bank.Bank
import java.util.Date

@Entity(tableName = "Saving")
data class Saving(
    @PrimaryKey(autoGenerate = true)
    val savingId: Int,
    val savingDate: Date,
    @Embedded
    val bank: Bank,
    val savingAmount: Double,
    val susuCollector: String
)
