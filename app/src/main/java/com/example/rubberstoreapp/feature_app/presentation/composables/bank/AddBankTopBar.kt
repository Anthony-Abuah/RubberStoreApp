package com.example.rubberstoreapp.feature_app.presentation.composables.bank

import androidx.compose.runtime.Composable
import com.example.rubberstoreapp.core.util.Constants.ADD_BANK
import com.example.rubberstoreapp.feature_app.presentation.composables.components.ScreenTopBar

@Composable
fun AddBankTopBar(
    navigateBack: () -> Unit
) {
    ScreenTopBar(label = ADD_BANK) {
        navigateBack()
    }
}