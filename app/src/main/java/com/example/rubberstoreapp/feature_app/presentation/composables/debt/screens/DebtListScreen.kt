package com.example.rubberstoreapp.feature_app.presentation.composables.debt.screens


import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rubberstoreapp.feature_app.presentation.composables.components.AddFloatingActionButton
import com.example.rubberstoreapp.feature_app.presentation.composables.components.AppTopBar
import com.example.rubberstoreapp.feature_app.presentation.composables.components.DeleteAlertDialog
import com.example.rubberstoreapp.feature_app.presentation.composables.debt.DebtContent
import com.example.rubberstoreapp.feature_app.presentation.composables.debt.DebtScreens
import com.example.rubberstoreapp.feature_app.presentation.view_model.DebtViewModel


@Composable
@ExperimentalMaterialApi
fun DebtListScreen(
    debtViewModel: DebtViewModel = hiltViewModel(),
    navigateToUpdateDebtScreen: (debtId: Int) -> Unit,
    navController: NavController
) {
    val debts by debtViewModel.allDebts.collectAsState(
        initial = emptyList()
    )

    Scaffold(
        topBar = {
            AppTopBar(label = "Debts")
        },
        content = { padding ->
            DebtContent(
                padding = padding,
                debts = debts,
                dialog = debtViewModel.openDialog,
                openDialog = { debtViewModel.openDialog() },
                closeDialog = { debtViewModel.closeDialog() },
                deleteDebt = { debt -> debtViewModel.deleteDebt(debt) },
                navigateToUpdateDebtScreen = navigateToUpdateDebtScreen
            )
        },
        floatingActionButton = {
            AddFloatingActionButton(
                navigateToAnotherScreen =
                {
                    navController.navigate(DebtScreens.AddDebtScreen.route)
                },
                description = "Add Debt")
        }
    )
}