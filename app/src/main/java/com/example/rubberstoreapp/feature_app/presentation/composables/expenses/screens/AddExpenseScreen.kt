package com.example.rubberstoreapp.feature_app.presentation.composables.expenses.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.feature_app.presentation.composables.expenses.AddExpenseContent
import com.example.rubberstoreapp.feature_app.presentation.composables.expenses.AddExpenseTopBar
import com.example.rubberstoreapp.feature_app.presentation.view_model.ExpenseViewModel

@Composable
fun AddExpenseScreen(
    expenseViewModel: ExpenseViewModel = hiltViewModel(),
    navigateBackToExpenseListScreen: () -> Unit
    ){

    Scaffold(
        topBar = {
            AddExpenseTopBar(
                navigateBack = navigateBackToExpenseListScreen
            )
        },
        content = { padding ->
            AddExpenseContent(
                paddingValues = padding,
                insertExpense = { expense ->
                    expenseViewModel.addExpense(expense)
                },
                navigateBack = navigateBackToExpenseListScreen
            )
        }
    )
}