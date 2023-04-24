package com.example.rubberstoreapp.feature_app.presentation.composables.expenses.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.feature_app.presentation.composables.expenses.UpdateExpenseContent
import com.example.rubberstoreapp.feature_app.presentation.composables.expenses.UpdateExpenseTopBar
import com.example.rubberstoreapp.feature_app.presentation.view_model.ExpenseViewModel


@Composable
fun UpdateExpenseScreen (
    expenseViewModel: ExpenseViewModel = hiltViewModel(),
    expenseId: Int,
    navigateBackToExpenseListScreen: () -> Unit
){


    LaunchedEffect(Unit) {
        expenseViewModel.getExpense(expenseId)
    }

    Scaffold(
        topBar = {
            UpdateExpenseTopBar(
                navigateBack = navigateBackToExpenseListScreen
            )
        },
        content = { padding ->
            UpdateExpenseContent(
                padding = padding,
                expense = expenseViewModel.expense,
                updateExpense = { expense->
                    expenseViewModel.updateExpense(expense)
                },
                updateExpenseName = {name->
                    expenseViewModel.updateExpenseName(name) },
                updateExpenseAmount = {amount->
                    expenseViewModel.updateExpenseAmount(amount) },
                updateExpenseDate = {date->
                    expenseViewModel.updateExpenseDate(date) },
                navigateBack = navigateBackToExpenseListScreen
            )
        }
    )
}