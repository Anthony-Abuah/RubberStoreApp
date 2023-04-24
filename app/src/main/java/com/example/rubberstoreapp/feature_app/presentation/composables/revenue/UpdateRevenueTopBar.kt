package com.example.rubberstoreapp.feature_app.presentation.composables.revenue

import androidx.compose.runtime.Composable
import com.example.rubberstoreapp.core.util.Constants.UPDATE_REVENUE
import com.example.rubberstoreapp.feature_app.presentation.composables.components.ScreenTopBar

@Composable
fun UpdateRevenueTopBar(
    navigateBack: () -> Unit
) {
    ScreenTopBar(label = UPDATE_REVENUE) {
        navigateBack()
    }
}