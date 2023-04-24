package com.example.rubberstoreapp.feature_app.presentation.composables.expenses

import androidx.compose.runtime.Composable
import com.example.rubberstoreapp.core.util.Constants.UPDATE_SUPPLIER
import com.example.rubberstoreapp.feature_app.presentation.composables.components.ScreenTopBar

@Composable
fun UpdateExpenseTopBar(
    navigateBack: () -> Unit
) {
    ScreenTopBar(label = UPDATE_SUPPLIER) {
        navigateBack()
    }
}