package com.example.rubberstoreapp.feature_app.presentation.composables.debt_repayment

import androidx.compose.runtime.Composable
import com.example.rubberstoreapp.core.util.Constants.UPDATE_DEBT_REPAYMENT
import com.example.rubberstoreapp.feature_app.presentation.composables.components.ScreenTopBar

@Composable
fun UpdateDebtRepaymentTopBar(
    navigateBack: () -> Unit
) {
    ScreenTopBar(label = UPDATE_DEBT_REPAYMENT) {
        navigateBack()
    }
}