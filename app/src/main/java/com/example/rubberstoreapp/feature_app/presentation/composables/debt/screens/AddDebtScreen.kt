package com.example.rubberstoreapp.feature_app.presentation.composables.debt.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.feature_app.presentation.composables.debt.AddDebtContent
import com.example.rubberstoreapp.feature_app.presentation.composables.debt.AddDebtTopBar
import com.example.rubberstoreapp.feature_app.presentation.view_model.DebtViewModel

@Composable
fun AddDebtScreen(
    debtViewModel: DebtViewModel = hiltViewModel(),
    navigateBackToDebtListScreen: () -> Unit,
    navigateToAddCustomerScreen: () -> Unit
    ){

    Scaffold(
        topBar = {
            AddDebtTopBar(
                navigateBack = navigateBackToDebtListScreen
            )
        },
        content = { padding ->
            AddDebtContent(
                paddingValues = padding,
                insertDebt = { debt ->
                    debtViewModel.addDebt(debt)
                },
                navigateBack = navigateBackToDebtListScreen,
                navigateToAddCustomerScreen = navigateToAddCustomerScreen
            )

        }
    )
}