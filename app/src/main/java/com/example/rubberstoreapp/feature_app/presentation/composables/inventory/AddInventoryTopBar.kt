package com.example.rubberstoreapp.feature_app.presentation.composables.inventory

import androidx.compose.runtime.Composable
import com.example.rubberstoreapp.core.util.Constants.ADD_INVENTORY
import com.example.rubberstoreapp.feature_app.presentation.composables.components.ScreenTopBar

@Composable
fun AddInventoryTopBar(
    navigateBack: () -> Unit
) {
    ScreenTopBar(label = ADD_INVENTORY) {
        navigateBack()
    }
}