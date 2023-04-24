package com.example.rubberstoreapp.feature_app.presentation.composables.customer

import androidx.compose.runtime.Composable
import com.example.rubberstoreapp.core.util.Constants.UPDATE_CUSTOMER
import com.example.rubberstoreapp.feature_app.presentation.composables.components.ScreenTopBar

@Composable
fun UpdateCustomerTopBar(
    navigateBack: () -> Unit
) {
    ScreenTopBar(label = UPDATE_CUSTOMER) {
        navigateBack
    }
}