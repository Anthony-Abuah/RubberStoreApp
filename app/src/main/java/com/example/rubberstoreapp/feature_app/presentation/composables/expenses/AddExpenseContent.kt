package com.example.rubberstoreapp.feature_app.presentation.composables.expenses

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.rubberstoreapp.R
import com.example.rubberstoreapp.core.util.Constants.ADD_EXPENSE
import com.example.rubberstoreapp.core.util.Constants.EXPENSE_AMOUNT
import com.example.rubberstoreapp.core.util.Constants.EXPENSE_NAME
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.expenses.Expense
import com.example.rubberstoreapp.feature_app.presentation.composables.components.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AddExpenseContent(
    paddingValues: PaddingValues,
    insertExpense: (expense: Expense) -> Unit,
    navigateBack: () -> Unit
) {
    var expenseName by remember { mutableStateOf(NO_VALUE) }
    var expenseDate by remember { mutableStateOf(LocalDate.now()) }

    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("EEE, dd MMM, yyyy")
                .format(expenseDate)
        }
    }

    var expenseAmount by remember { mutableStateOf(NO_VALUE) }

    val context = LocalContext.current

    BasicScreenColumn {
        BasicImage(
            drawableResource = R.drawable.expenses,
            width = 200.dp,
            height = 200.dp,
            backgroundColor = Color.LightGray
        )

        Spacer(modifier = Modifier.height(50.dp))

        BasicTextField(
            value = expenseName,
            onValueChange = { name -> expenseName = name },
            placeholder = EXPENSE_NAME,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(25.dp))

        SelectDate(
            selectedDate = { date ->
                expenseDate = date
            },
            initialDate = LocalDate.now(),
            displayedDate = formattedDate
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = expenseAmount,
            onValueChange = { amount -> expenseAmount = amount },
            placeholder = EXPENSE_AMOUNT,
            keyboardType = KeyboardType.Decimal
        )

        Spacer(modifier = Modifier.height(50.dp))

        BasicButton(
            onClick = {
                val date = java.sql.Date.valueOf(expenseDate.toString())
                val amount = if (expenseAmount.isBlank()) { 0.0 } else expenseAmount.toDouble()
                val expense = Expense(0, date, expenseName, amount)

                if (expenseName.isBlank()) {
                    Toast.makeText(context, "Please enter  expense name", Toast.LENGTH_LONG).show()
                } else if (amount <= 0.0) {
                    Toast.makeText(context, "Please enter expense amount", Toast.LENGTH_LONG)
                        .show()
                } else {
                    insertExpense(expense)
                    Toast.makeText(context, "Expense Added Successfully", Toast.LENGTH_LONG).show()
                    navigateBack()
                }
            },
            buttonName = ADD_EXPENSE
        )

    }
}