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
import com.example.rubberstoreapp.core.util.Constants.ADD_DEBT_REPAYMENT
import com.example.rubberstoreapp.core.util.Constants.DEBT_REPAYMENT_AMOUNT
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.core.util.Constants.SELECT_CUSTOMER
import com.example.rubberstoreapp.feature_app.data.local.debt_repayment.DebtRepayment
import com.example.rubberstoreapp.feature_app.presentation.composables.components.*
import com.example.rubberstoreapp.feature_app.presentation.view_model.CustomerViewModel
import com.example.rubberstoreapp.feature_app.presentation.view_model.DebtViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AddDebtRepaymentContent(
    customerViewModel: CustomerViewModel = hiltViewModel(),
    debtViewModel: DebtViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    getSumOfDebtRepaymentByCustomer: (customerName: String) -> Unit,
    debtRepaid: Double,
    insertDebtRepayment: (debtRepayment: DebtRepayment) -> Unit,
    navigateBack: () -> Unit,
    navigateToAddCustomerScreen: () -> Unit,
) {

    val customers by customerViewModel.customers.collectAsState(
        initial = emptyList()
    )


    var customerDebt by remember {
        mutableStateOf(0.0)
    }
    var customerDebtPayment by remember {
        mutableStateOf(0.0)
    }

    val customerNames = mutableListOf<String>()

    for (customer in customers) {
        customerNames.add(customer.customerName)
    }

    var debtRepaymentDate by remember { mutableStateOf(LocalDate.now()) }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("EEE, dd MMM, yyyy")
                .format(debtRepaymentDate)
        }
    }

    var debtRepaymentAmount by remember { mutableStateOf(NO_VALUE) }
    var customerName by remember { mutableStateOf(NO_VALUE) }
    val context = LocalContext.current

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
                debtRepaymentDate = date
            },
            initialDate = LocalDate.now(),
            displayedDate = formattedDate
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = debtRepaymentAmount,
            onValueChange = { amount ->
                debtRepaymentAmount = amount
            },
            placeholder = DEBT_REPAYMENT_AMOUNT,
            keyboardType = KeyboardType.Decimal
        )

        Spacer(modifier = Modifier.height(25.dp))

        AutoCompleteWithAddButton(
            label = SELECT_CUSTOMER,
            listItems = customerNames,
            onClick = navigateToAddCustomerScreen
        ) { _customerName ->
            customerName = _customerName
            customerViewModel.getCustomer(_customerName)
            debtViewModel.getTotalDebtOfCustomer(_customerName)
            getSumOfDebtRepaymentByCustomer(_customerName)
        }

        Spacer(modifier = Modifier.height(50.dp))

        BasicButton(
            onClick = {
                val date = java.sql.Date.valueOf(debtRepaymentDate.toString())
                val amount = if (debtRepaymentAmount.isBlank()) {
                    0.0
                } else debtRepaymentAmount.toDouble()

                customerDebtPayment = debtRepaid

                customerDebt = debtViewModel.totalDebtOfCustomer

                val currentDebt = customerDebt - customerDebtPayment
                val customer = customerViewModel.customer
                val debtRepayment = DebtRepayment(0, date, customer, amount)

                if (amount <= 0.0) {
                    Toast.makeText(
                        context,
                        "Please enter Debt Repayment amount ",
                        Toast.LENGTH_LONG
                    ).show()
                } else if (customerName.isBlank()) {
                    Toast.makeText(context, "Please select customer", Toast.LENGTH_LONG).show()
                } else if (!(customerNames.contains(customerName))) {
                    Toast.makeText(context, "$customerName is not in your customer list", Toast.LENGTH_LONG).show()
                    Toast.makeText(context, "Please add $customerName to your customer list", Toast.LENGTH_LONG).show()

                } else if (currentDebt == 0.0){
                    Toast.makeText(context, "${customer.customerName} does not have any debt to pay", Toast.LENGTH_LONG).show()
                }
                else if (amount > (currentDebt)){
                    Toast.makeText(context, "${customer.customerName} only owes GHS $currentDebt", Toast.LENGTH_LONG).show()
                }
                else {
                    insertDebtRepayment(debtRepayment)
                    Toast.makeText(context, "${customer.customerName} has payed GHS $amount of his GHS $currentDebt debt", Toast.LENGTH_LONG).show()
                    navigateBack()
                }
            },
            buttonName = ADD_DEBT_REPAYMENT
        )
    }
}