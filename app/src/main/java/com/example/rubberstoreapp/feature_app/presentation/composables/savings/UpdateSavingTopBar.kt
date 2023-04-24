package com.example.rubberstoreapp.feature_app.presentation.composables.savings

import androidx.compose.runtime.Composable
import com.example.rubberstoreapp.core.util.Constants.UPDATE_SAVINGS
import com.example.rubberstoreapp.feature_app.presentation.composables.components.ScreenTopBar

@Composable
fun UpdateSavingTopBar(
    navigateBack: () -> Unit
) {
    ScreenTopBar(label = UPDATE_SAVINGS) {
        navigateBack()
    }
}