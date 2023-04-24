package com.example.rubberstoreapp.feature_app.presentation.composables.expenses.screens


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
import com.example.rubberstoreapp.feature_app.presentation.composables.expenses.ExpenseContent
import com.example.rubberstoreapp.feature_app.presentation.composables.expenses.ExpenseScreens
import com.example.rubberstoreapp.feature_app.presentation.view_model.ExpenseViewModel


@Composable
@ExperimentalMaterialApi
fun ExpenseListScreen(
    expenseViewModel: ExpenseViewModel = hiltViewModel(),
    navigateToUpdateExpenseScreen: (expenseId: Int) -> Unit,
    navController: NavController
) {
    val expenses by expenseViewModel.allExpenses.collectAsState(
        initial = emptyList()
    )

    Scaffold(
        topBar = {
            AppTopBar(label = "Expenses")
        },
        content = { padding ->
            ExpenseContent(
                padding = padding,
                expenses = expenses,
                dialog = expenseViewModel.openDialog ,
                openDialog = { expenseViewModel.openDialog() },
                closeDialog = { expenseViewModel.closeDialog() },
                deleteExpense = { expense ->
                        expenseViewModel.deleteExpense(expense)
                },
                navigateToUpdateExpenseScreen = navigateToUpdateExpenseScreen
            )
        },
        floatingActionButton = {
            AddFloatingActionButton(
                navigateToAnotherScreen =
                {
                    navController.navigate(ExpenseScreens.AddExpenseScreen.route)
                },
                description = "Add Expense")
        }
    )
}