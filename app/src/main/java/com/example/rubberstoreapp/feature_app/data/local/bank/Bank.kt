package com.example.rubberstoreapp.feature_app.data.local.bank

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Bank")
data class Bank(
    @PrimaryKey(autoGenerate = true)
    val bankId: Int,
    val bankName: String,
    val bankContact: String,
    val bankLocation: String
)
