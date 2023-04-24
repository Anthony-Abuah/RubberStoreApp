package com.example.rubberstoreapp.feature_app.presentation.view_model.states

import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import java.util.Date

data class ExpenseState (
    val expenseId: Int = 0,
    val expenseName: String = NO_VALUE,
    val expenseDate: Date = Date(),
    val expenseAmount: Double = 0.0
)