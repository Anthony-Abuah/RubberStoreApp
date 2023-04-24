package com.example.rubberstoreapp.feature_app.presentation.view_model.states

import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import java.util.Date

data class DebtState (
    val debtId: Int = 0,
    val debtDate: Date = Date(),
    val debtAmount: Double = 0.0
)