package com.example.rubberstoreapp.feature_app.presentation.composables.debt_repayment.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.feature_app.presentation.composables.debt_repayment.UpdateDebtRepaymentContent
import com.example.rubberstoreapp.feature_app.presentation.composables.debt_repayment.UpdateDebtRepaymentTopBar
import com.example.rubberstoreapp.feature_app.presentation.view_model.DebtRepaymentViewModel


@Composable
fun UpdateDebtRepaymentScreen(
    debtRepaymentViewModel: DebtRepaymentViewModel = hiltViewModel(),
    debtRepaymentId: Int,
    navigateBackToDebtRepaymentListScreen: () -> Unit,
    navigateToAddCustomerScreen: () -> Unit
){

    LaunchedEffect(Unit) {
        debtRepaymentViewModel.getDebtRepayment(debtRepaymentId)
    }


    Scaffold(
        topBar = {
            UpdateDebtRepaymentTopBar(
                navigateBack = navigateBackToDebtRepaymentListScreen
            )
        },
        content = { padding ->
            UpdateDebtRepaymentContent(
                paddingValues = padding,
                getSumOfDebtRepaymentByCustomer = { customerName ->
                    debtRepaymentViewModel.getSumOfDebtRepaymentByCustomer(customerName)
                },
                debtRepaid = debtRepaymentViewModel.sumOfDebtRepaymentByCustomer?:0.0,
                debtRepayment = debtRepaymentViewModel.debtRepayment,
                selectedItem = debtRepaymentViewModel.customerName.customerName,
                updateDebtRepayment = {debtRepayment->
                    debtRepaymentViewModel.updateDebtRepayment(debtRepayment)
                },
                updateCustomerName = { customerName->
                    debtRepaymentViewModel.updateCustomerName(customerName)
                },
                updateDebtRepaymentDate = {date ->
                    debtRepaymentViewModel.updateDebtRepaymentDate(date)
                },
                updateDebtRepaymentAmount = {amount ->
                    debtRepaymentViewModel.updateDebtRepaymentAmount(amount)
                },
                navigateBack =  navigateBackToDebtRepaymentListScreen
            ) {
                navigateToAddCustomerScreen()
            }
        }
    )
}