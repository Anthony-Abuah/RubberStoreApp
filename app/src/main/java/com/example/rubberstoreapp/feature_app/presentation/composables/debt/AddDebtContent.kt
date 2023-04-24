package com.example.rubberstoreapp.feature_app.presentation.composables.debt

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.R
import com.example.rubberstoreapp.core.util.Constants.ADD_DEBT
import com.example.rubberstoreapp.core.util.Constants.DEBT_AMOUNT
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.core.util.Constants.SELECT_CUSTOMER
import com.example.rubberstoreapp.feature_app.data.local.debt.Debt
import com.example.rubberstoreapp.feature_app.presentation.composables.components.*
import com.example.rubberstoreapp.feature_app.presentation.view_model.CustomerViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AddDebtContent(
    customerViewModel: CustomerViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    insertDebt: (debt: Debt) -> Unit,
    navigateBack: () -> Unit,
    navigateToAddCustomerScreen: () -> Unit,
) {
    val customers by customerViewModel.customers.collectAsState(
        initial = emptyList()
    )

    val context = LocalContext.current

    val customerNames = mutableListOf<String>()

    for (customer in customers) {
        customerNames.add(customer.customerName)
    }

    var debtDate by remember { mutableStateOf(LocalDate.now()) }

    var customerName by remember { mutableStateOf(NO_VALUE) }

    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("EEE, dd MMM, yyyy")
                .format(debtDate)
        }
    }

    var debtAmount by remember { mutableStateOf(NO_VALUE) }

    BasicScreenColumn {
        BasicImage(
            drawableResource = R.drawable.debt,
            width = 200.dp,
            height = 200.dp,
            backgroundColor = Color.LightGray
        )

        Spacer(modifier = Modifier.height(50.dp))

        SelectDate(
            selectedDate = { selectedDate ->
                debtDate = selectedDate
            },
            initialDate = LocalDate.now(),
            displayedDate = formattedDate
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = debtAmount,
            onValueChange = { amount ->
                debtAmount = amount
            },
            placeholder = DEBT_AMOUNT,
            keyboardType = KeyboardType.Decimal
        )

        Spacer(modifier = Modifier.height(25.dp))

        AutoCompleteWithAddButton(
            label = SELECT_CUSTOMER,
            listItems = customerNames,
            onClick = navigateToAddCustomerScreen
        ) { _customerName ->
            customerName = _customerName
            if(customerNames.contains(_customerName)){
                customerViewModel.getCustomer(_customerName)
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        BasicButton(
            onClick = {
                val date = java.sql.Date.valueOf(debtDate.toString())
                val amount = if (debtAmount.isBlank()) {
                    0.0
                } else debtAmount.toDouble()


                val customer = customerViewModel.customer
                val debt = Debt(0, date, customer, amount)

                if (amount <= 0.0) {
                    Toast.makeText(context, "Please enter debt amount ", Toast.LENGTH_LONG).show()
                } else if ((customerName.isBlank())) {
                    Toast.makeText(context, "Please select customer", Toast.LENGTH_LONG).show()
                } else if (!(customerNames.contains(customerName))) {
                    Toast.makeText(context, "$customerName is not in your customer list", Toast.LENGTH_LONG).show()
                    Toast.makeText(context, "Please add $customerName to your customer list", Toast.LENGTH_LONG).show()
                } else {
                    insertDebt(debt)
                    Toast.makeText(context, "Debt Added", Toast.LENGTH_LONG).show()
                    navigateBack()
                }
            },
            buttonName = ADD_DEBT
        )
    }
}
