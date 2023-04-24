package com.example.rubberstoreapp.feature_app.presentation.composables.savings

import androidx.compose.runtime.Composable
import com.example.rubberstoreapp.core.util.Constants.ADD_SAVINGS
import com.example.rubberstoreapp.feature_app.presentation.composables.components.ScreenTopBar

@Composable
fun AddSavingTopBar(
    navigateBack: () -> Unit
) {
    ScreenTopBar(label = ADD_SAVINGS) {
        navigateBack()
    }
}