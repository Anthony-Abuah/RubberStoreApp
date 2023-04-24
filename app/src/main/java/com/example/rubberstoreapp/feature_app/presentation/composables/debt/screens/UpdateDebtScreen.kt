package com.example.rubberstoreapp.feature_app.presentation.composables.debt.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.feature_app.presentation.composables.debt.UpdateDebtContent
import com.example.rubberstoreapp.feature_app.presentation.composables.debt.UpdateDebtTopBar
import com.example.rubberstoreapp.feature_app.presentation.view_model.DebtViewModel


@Composable
fun UpdateDebtScreen(
    debtViewModel: DebtViewModel = hiltViewModel(),
    debtId: Int,
    navigateBackToDebtListScreen: () -> Unit,
    navigateToAddCustomerScreen: () -> Unit
){

    LaunchedEffect(Unit) {
        debtViewModel.getDebt(debtId)
    }

    Scaffold(
        topBar = {
            UpdateDebtTopBar(
                navigateBack = navigateBackToDebtListScreen
            )
        },
        content = { padding ->
            UpdateDebtContent(
                paddingValues = padding,
                debt = debtViewModel.debt,
                updateDebt = {debt->
                    debtViewModel.updateDebt(debt)
                 },
                updateCustomerName = { customerName->
                     debtViewModel.updateCustomerName(customerName)
                },
                updateDebtDate = {date ->
                    debtViewModel.updateDebtDate(date)
                },
                updateDebtAmount = {amount ->
                    debtViewModel.updateDebtAmount(amount)
                },
                selectedItem = debtViewModel.customerName.customerName,
                navigateBack =  navigateBackToDebtListScreen ) {
                navigateToAddCustomerScreen()
            }
        }
    )
}