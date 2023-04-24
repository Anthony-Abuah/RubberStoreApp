package com.example.rubberstoreapp.feature_app.presentation.composables.debt_repayment.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.feature_app.presentation.composables.debt_repayment.AddDebtRepaymentContent
import com.example.rubberstoreapp.feature_app.presentation.composables.debt_repayment.AddDebtRepaymentTopBar
import com.example.rubberstoreapp.feature_app.presentation.view_model.DebtRepaymentViewModel

@Composable
fun AddDebtRepaymentScreen(
    debtRepaymentViewModel: DebtRepaymentViewModel = hiltViewModel(),
    navigateBackToDebtRepaymentListScreen: () -> Unit,
    navigateToAddCustomerScreen: () -> Unit
    ){


    Scaffold(
        topBar = {
            AddDebtRepaymentTopBar(
                navigateBack = navigateBackToDebtRepaymentListScreen
            )
        },
        content = { padding ->
            AddDebtRepaymentContent(
                paddingValues = padding,
                getSumOfDebtRepaymentByCustomer = {customerName->
                    debtRepaymentViewModel.getSumOfDebtRepaymentByCustomer(customerName)},
                debtRepaid = debtRepaymentViewModel.sumOfDebtRepaymentByCustomer?:0.0,
                insertDebtRepayment = { debtRepayment ->
                    debtRepaymentViewModel.addDebtRepayment(debtRepayment)
                },
                navigateBack = navigateBackToDebtRepaymentListScreen,
                navigateToAddCustomerScreen = navigateToAddCustomerScreen
            )
        }
    )
}