package com.example.rubberstoreapp.feature_app.presentation.composables.customer

import androidx.compose.runtime.Composable
import com.example.rubberstoreapp.core.util.Constants.ADD_CUSTOMER
import com.example.rubberstoreapp.feature_app.presentation.composables.components.ScreenTopBar

@Composable
fun AddCustomerTopBar(
    navigateBack: () -> Unit
) {
    ScreenTopBar(label = ADD_CUSTOMER) {
        navigateBack()
    }
}