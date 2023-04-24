package com.example.rubberstoreapp.feature_app.presentation.composables.expenses

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.expenses.Expense
import com.example.rubberstoreapp.feature_app.domain.repository.Expenses
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicScreenColumn
import com.example.rubberstoreapp.feature_app.presentation.composables.components.DeleteAlertDialog
import java.util.*

@Composable
@ExperimentalMaterialApi
fun ExpenseContent(
    padding: PaddingValues,
    expenses: Expenses,
    dialog: Boolean,
    openDialog: () -> Unit,
    closeDialog: () -> Unit,
    deleteExpense: (expense: Expense) -> Unit,
    navigateToUpdateExpenseScreen: (expenseId: Int) -> Unit
) {
    var expense by remember {
        mutableStateOf(Expense(0, Date(), NO_VALUE, 0.0))
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)
    ) {
        items(
            items = expenses
        ) { theCurrentExpense ->
            ExpenseCard(
                expense = theCurrentExpense,
                currentExpense = { currentExpense ->
                    openDialog()
                    expense = currentExpense
                },
                navigateToUpdateExpenseScreen = navigateToUpdateExpenseScreen
            )
        }
    }

    DeleteAlertDialog(
        dialogTitle = "Delete Expense",
        dialogMessage = "Are you sure you want to delete expense?",
        openDialog = dialog,
        delete = { deleteExpense(expense) },
        closeDialog = closeDialog
    )

}