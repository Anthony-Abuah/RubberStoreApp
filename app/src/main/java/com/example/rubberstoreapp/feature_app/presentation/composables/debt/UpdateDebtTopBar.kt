package com.example.rubberstoreapp.feature_app.presentation.composables.debt

import androidx.compose.runtime.Composable
import com.example.rubberstoreapp.core.util.Constants.UPDATE_DEBT
import com.example.rubberstoreapp.feature_app.presentation.composables.components.ScreenTopBar

@Composable
fun UpdateDebtTopBar(
    navigateBack: () -> Unit
) {
    ScreenTopBar(label = UPDATE_DEBT) {
        navigateBack()
    }
}