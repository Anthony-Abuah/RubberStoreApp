package com.example.rubberstoreapp.feature_app.presentation.composables.supplier

import androidx.compose.runtime.Composable
import com.example.rubberstoreapp.core.util.Constants.ADD_SUPPLIER
import com.example.rubberstoreapp.feature_app.presentation.composables.components.ScreenTopBar

@Composable
fun AddSupplierTopBar(
    navigateBack: () -> Unit
) {
    ScreenTopBar(label = ADD_SUPPLIER) {
        navigateBack()
    }
}