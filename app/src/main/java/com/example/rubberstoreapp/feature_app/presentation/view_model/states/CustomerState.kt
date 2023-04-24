package com.example.rubberstoreapp.feature_app.presentation.view_model.states

import androidx.lifecycle.LiveData
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.customer.Customer

data class CustomerState (
    val customerId: Int = 0,
    val customerName: String = NO_VALUE,
    val customerContact: String = NO_VALUE,
    val customerLocation: String = NO_VALUE,
    val customerInfo: String = NO_VALUE

)