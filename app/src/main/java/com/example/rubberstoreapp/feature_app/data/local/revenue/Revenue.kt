package com.example.rubberstoreapp.feature_app.data.local.revenue

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Revenue")
data class Revenue(
    @PrimaryKey(autoGenerate = true)
    val revenueId: Int,
    val date: Date,
    val amount: Double
)
