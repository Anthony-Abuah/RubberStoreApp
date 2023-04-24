package com.example.rubberstoreapp.feature_app.presentation.composables.debt_repayment

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
import com.example.rubberstoreapp.core.util.Constants.DEBT_REPAYMENT_AMOUNT
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.core.util.Constants.SELECT_CUSTOMER
import com.example.rubberstoreapp.core.util.Constants.UPDATE_DEBT_REPAYMENT
import com.example.rubberstoreapp.feature_app.data.local.debt_repayment.DebtRepayment
import com.example.rubberstoreapp.feature_app.presentation.composables.components.*
import com.example.rubberstoreapp.feature_app.presentation.view_model.CustomerViewModel
import com.example.rubberstoreapp.feature_app.presentation.view_model.DebtViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@Composable
fun UpdateDebtRepaymentContent(
    customerViewModel: CustomerViewModel = hiltViewModel(),
    debtViewModel: DebtViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    getSumOfDebtRepaymentByCustomer: (customerName: String) -> Unit,
    debtRepaid: Double,
    debtRepayment: DebtRepayment,
    selectedItem: String,
    updateDebtRepayment: (debtRepayment: DebtRepayment) -> Unit,
    updateCustomerName: (customerName: String) -> Unit,
    updateDebtRepaymentDate: (date: Date) -> Unit,
    updateDebtRepaymentAmount: (amount: Double) -> Unit,
    navigateBack: () -> Unit,
    navigateToAddCustomerScreen: () -> Unit,
) {
    val shortFormat = "yyyy-MM-dd"
    val shortDateFormat = SimpleDateFormat(shortFormat, Locale.UK)

    val longFormat = "EEE, dd MMM, yyyy"
    val longDateFormat = SimpleDateFormat(longFormat, Locale.UK)

    val context = LocalContext.current

    val customers by customerViewModel.customers.collectAsState(
        initial = emptyList()
    )

    var customerDebt by remember {
        mutableStateOf(0.0)
    }
    var customerDebtPayment by remember {
        mutableStateOf(0.0)
    }

    var customerName by remember {
        mutableStateOf(NO_VALUE)
    }

    val customerNames = mutableListOf<String>()

    for (customer in customers) {
        customerNames.add(customer.customerName)
    }


    BasicScreenColumn {
        BasicImage(
            drawableResource = R.drawable.debt_repayment,
            width = 200.dp,
            height = 200.dp,
            backgroundColor = Color.LightGray
        )

        Spacer(modifier = Modifier.height(50.dp))

        SelectDate(
            selectedDate = { date ->
                val updatedDate = java.sql.Date.valueOf(date.toString())
                updateDebtRepaymentDate(updatedDate)
            },
            initialDate = LocalDate.parse(shortDateFormat.format(debtRepayment.date)),
            displayedDate = longDateFormat.format(debtRepayment.date)
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = debtRepayment.amount.toString(),
            onValueChange = { amount ->

                updateDebtRepaymentAmount(amount.toDouble())
            },
            placeholder = DEBT_REPAYMENT_AMOUNT,
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
                customerViewModel.getCustomer(_customerName)
                debtViewModel.getTotalDebtOfCustomer(_customerName)
                getSumOfDebtRepaymentByCustomer(_customerName)

            }
        )

        Spacer(modifier = Modifier.height(50.dp))

        BasicButton(
            onClick = {
                val date = debtRepayment.date
                val amount = debtRepayment.amount
                val customer = customerViewModel.customer

                customerDebtPayment = debtRepaid

                customerDebt = debtViewModel.totalDebtOfCustomer

                val updatedDebtRepayment =
                    DebtRepayment(debtRepayment.debtRepaymentId, date, customer, amount)

                val currentDebt = customerDebt - customerDebtPayment

                if (amount <= 0.0) {
                    Toast.makeText(
                        context,
                        "Please enter debt Repayment amount ",
                        Toast.LENGTH_LONG
                    ).show()
                } else if (customerName.isBlank()) {
                    Toast.makeText(context, "Please select customer", Toast.LENGTH_LONG).show()
                } else if (!(customerNames.contains(customerName))) {
                    Toast.makeText(context, "$customerName is not in your customer List", Toast.LENGTH_LONG).show()
                    Toast.makeText(context, "Please add $customerName to your customer List", Toast.LENGTH_LONG).show()
                }

                else if ((amount-customerDebtPayment) > (currentDebt)){
                    Toast.makeText(context, "${customer.customerName} owes GHS $currentDebt", Toast.LENGTH_LONG).show()
                }
                else {
                    updateDebtRepayment(updatedDebtRepayment)
                    Toast.makeText(context, "${customer.customerName} has payed GHS $amount of his debts", Toast.LENGTH_LONG).show()
                    navigateBack()
                }
             },
            buttonName = UPDATE_DEBT_REPAYMENT
        )
    }
}
