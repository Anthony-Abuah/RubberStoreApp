package com.example.rubberstoreapp.feature_app.presentation.composables.debt_repayment.screens


import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rubberstoreapp.feature_app.presentation.composables.components.AddFloatingActionButton
import com.example.rubberstoreapp.feature_app.presentation.composables.components.AppTopBar
import com.example.rubberstoreapp.feature_app.presentation.composables.debt_repayment.DebtRepaymentContent
import com.example.rubberstoreapp.feature_app.presentation.composables.debt_repayment.DebtRepaymentScreens
import com.example.rubberstoreapp.feature_app.presentation.view_model.DebtRepaymentViewModel


@Composable
@ExperimentalMaterialApi
fun DebtRepaymentListScreen(
    debtRepaymentViewModel: DebtRepaymentViewModel = hiltViewModel(),
    navigateToUpdateDebtRepaymentScreen: (debtRepaymentId: Int) -> Unit,
    navController: NavController
) {
    val debtRepayments by debtRepaymentViewModel.allDebtRepayments.collectAsState(
        initial = emptyList()
    )

    Scaffold(
        topBar = {
            AppTopBar(label = "DebtRepayment")
        },
        content = { padding ->
            DebtRepaymentContent(
                padding = padding,
                debtRepayments = debtRepayments,
                dialog = debtRepaymentViewModel.openDialog,
                openDialog = { debtRepaymentViewModel.openDialog() },
                closeDialog = { debtRepaymentViewModel.closeDialog() },
                deleteDebtRepayment = { debtRepayment ->
                        debtRepaymentViewModel.deleteDebtRepayment(debtRepayment)
                },
                navigateToUpdateDebtRepaymentScreen = navigateToUpdateDebtRepaymentScreen
            )

        },
        floatingActionButton = {
            AddFloatingActionButton(
                navigateToAnotherScreen =
                {
                    navController.navigate(DebtRepaymentScreens.AddDebtRepaymentScreen.route)
                },
                description = "Add DebtRepayment")
        }
    )
}