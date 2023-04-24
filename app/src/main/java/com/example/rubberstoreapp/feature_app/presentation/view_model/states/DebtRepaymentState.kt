package com.example.rubberstoreapp.feature_app.presentation.view_model.states

import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.customer.Customer
import java.util.*

data class DebtRepaymentState (
    val debtRepaymentId: Int = 0,
    val date: Date = Date(),
    val customer: Customer = Customer(0, NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE),
    val amount: Double = 0.0
)