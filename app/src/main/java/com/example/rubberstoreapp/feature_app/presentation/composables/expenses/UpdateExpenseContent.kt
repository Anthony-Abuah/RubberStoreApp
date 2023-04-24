package com.example.rubberstoreapp.feature_app.presentation.composables.expenses

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.rubberstoreapp.R
import com.example.rubberstoreapp.core.util.Constants.EXPENSE_AMOUNT
import com.example.rubberstoreapp.core.util.Constants.EXPENSE_NAME
import com.example.rubberstoreapp.core.util.Constants.UPDATE_EXPENSE
import com.example.rubberstoreapp.feature_app.data.local.expenses.Expense
import com.example.rubberstoreapp.feature_app.presentation.composables.components.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


@Composable
fun UpdateExpenseContent(
    padding: PaddingValues,
    expense: Expense,
    updateExpense: (expense: Expense) -> Unit,
    updateExpenseName: (name: String) -> Unit,
    updateExpenseDate: (date: Date) -> Unit,
    updateExpenseAmount: (amount: Double) -> Unit,
    navigateBack: () -> Unit
) {
    val shortFormat = "yyyy-MM-dd"
    val shortDateFormat = SimpleDateFormat(shortFormat, Locale.UK)

    val longFormat = "EEE, dd MMM, yyyy"
    val longDateFormat = SimpleDateFormat(longFormat, Locale.UK)


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
            value = expense.expenseName,
            onValueChange = { name ->
                updateExpenseName(name)
            },
            placeholder = EXPENSE_NAME,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(25.dp))

        SelectDate(
            selectedDate = { date ->
                val toDate = java.sql.Date.valueOf(date.toString())
                updateExpenseDate(toDate)
            },
            initialDate = LocalDate.parse(shortDateFormat.format(expense.date)),
            displayedDate = longDateFormat.format(expense.date)
        )

        Spacer(modifier = Modifier.width(25.dp))

        BasicTextField(
            value = expense.expenseAmount.toString(),
            onValueChange = { amount ->
                updateExpenseAmount(amount.toDouble())
            },
            placeholder = EXPENSE_AMOUNT,
            keyboardType = KeyboardType.Decimal
        )

        Spacer(modifier = Modifier.width(50.dp))

        BasicButton(
            onClick = {
                val date = expense.date
                val name = expense.expenseName
                val amount = expense.expenseAmount
                val updatedExpense = Expense(expense.expenseId, date, name, amount)

                if (name.isBlank()) {
                    Toast.makeText(context, "Please enter expense name", Toast.LENGTH_LONG).show()
                } else if (amount <= 0.0) {
                    Toast.makeText(context, "Please enter expense amount", Toast.LENGTH_LONG)
                        .show()
                } else {
                    updateExpense(updatedExpense)
                    Toast.makeText(context, "Expense Updated", Toast.LENGTH_LONG).show()
                    navigateBack()
                }
            },
            buttonName = UPDATE_EXPENSE
        )
    }
}