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
import com.example.rubberstoreapp.core.util.Constants.DEBT_AMOUNT
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.core.util.Constants.SELECT_CUSTOMER
import com.example.rubberstoreapp.core.util.Constants.UPDATE_DEBT
import com.example.rubberstoreapp.feature_app.data.local.debt.Debt
import com.example.rubberstoreapp.feature_app.presentation.composables.components.*
import com.example.rubberstoreapp.feature_app.presentation.view_model.CustomerViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@Composable
fun UpdateDebtContent(
    customerViewModel: CustomerViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    debt: Debt,
    selectedItem: String,
    updateDebt: (debt: Debt) -> Unit,
    updateCustomerName: (customerName: String) -> Unit,
    updateDebtDate: (date: Date) -> Unit,
    updateDebtAmount: (amount: Double) -> Unit,
    navigateBack: () -> Unit,
    navigateToAddCustomerScreen: () -> Unit,
) {
    val shortFormat = "yyyy-MM-dd"
    val shortDateFormat = SimpleDateFormat(shortFormat, Locale.UK)

    val longFormat = "EEE, dd MMM, yyyy"
    val longDateFormat = SimpleDateFormat(longFormat, Locale.UK)

    val customers by customerViewModel.customers.collectAsState(
        initial = emptyList()
    )

    val context = LocalContext.current

    val customerNames = mutableListOf<String>()

    for (customer in customers) {
        customerNames.add(customer.customerName)
    }

    var customerName by remember {
        mutableStateOf(NO_VALUE)
    }

    BasicScreenColumn {

        BasicImage(
            drawableResource = R.drawable.debt,
            width = 200.dp,
            height = 200.dp,
            backgroundColor = Color.LightGray
        )

        Spacer(modifier = Modifier.height(50.dp))

        SelectDate(
            selectedDate = {date->
                val updatedDate = java.sql.Date.valueOf(date.toString())
                updateDebtDate(updatedDate)
            },
            initialDate = LocalDate.parse(shortDateFormat.format(debt.date)),
            displayedDate = longDateFormat.format(debt.date))

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = debt.amount.toString(),
            onValueChange = {amount->
                updateDebtAmount(amount.toDouble())
            },
            placeholder = DEBT_AMOUNT,
            keyboardType = KeyboardType.Decimal
        )

        Spacer(modifier = Modifier.height(25.dp))

        UpdateAutoCompleteWithAddButton(
            label = SELECT_CUSTOMER,
            listItems = customerNames,
            onClick = navigateToAddCustomerScreen,
            selectedItem = selectedItem,
            getInfo = {_customerName->
                customerName = _customerName
                updateCustomerName(_customerName)
                if(customerNames.contains(_customerName)){
                    customerViewModel.getCustomer(_customerName)
                }

            })

        Spacer(modifier = Modifier.height(50.dp))

        BasicButton(
            onClick = {
                val date = debt.date
                val amount = debt.amount
                val customer = customerViewModel.customer

                val updatedDebt = Debt(debt.debtId, date, customer, amount)

                if (amount <= 0.0) {
                    Toast.makeText(context, "Please enter debt amount ", Toast.LENGTH_LONG).show()
                }  else if (customerName.isBlank()) {
                    Toast.makeText(context, "Please Select customer name", Toast.LENGTH_LONG).show()
                } else if (!(customerNames.contains(customerName))) {
                    Toast.makeText(context, "$customerName is not in your customer list", Toast.LENGTH_LONG).show()
                    Toast.makeText(context, "Please add $customerName to your customer list", Toast.LENGTH_LONG).show()
                } else {
                    updateDebt(updatedDebt)
                    Toast.makeText(context, "Debt Updated", Toast.LENGTH_LONG).show()
                    navigateBack()
                }
            },
            buttonName = UPDATE_DEBT
        )
    }

}
