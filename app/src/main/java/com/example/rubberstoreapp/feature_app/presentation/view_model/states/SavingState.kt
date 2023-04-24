package com.example.rubberstoreapp.feature_app.presentation.view_model.states

import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.bank.Bank
import java.util.*

data class SavingState (
    val savingId: Int = 0,
    val savingDate: Date = Date(),
    val bank: Bank = Bank(0, NO_VALUE, NO_VALUE, NO_VALUE),
    val savingAmount: Double = 0.0,
    val susuCollector: String? = NO_VALUE
)