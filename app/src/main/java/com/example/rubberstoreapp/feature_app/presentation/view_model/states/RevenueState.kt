package com.example.rubberstoreapp.feature_app.presentation.view_model.states

import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import java.util.Date

data class RevenueState (
    val revenueId: Int = 0,
    val revenueDate: Date = Date(),
    val revenueAmount: Double = 0.0
)