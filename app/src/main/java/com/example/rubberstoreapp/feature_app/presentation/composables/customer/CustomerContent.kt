package com.example.rubberstoreapp.feature_app.presentation.composables.customer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.customer.Customer
import com.example.rubberstoreapp.feature_app.domain.repository.Customers
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicLazyListScreenColumn
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicScreenColumn
import com.example.rubberstoreapp.feature_app.presentation.composables.components.DeleteAlertDialog

@Composable
@ExperimentalMaterialApi
fun CustomerContent(
    dialog: Boolean,
    customers: Customers,
    openDialog: () -> Unit,
    closeDialog: () -> Unit,
    deleteCustomer: (customer: Customer) -> Unit,
    navigateToUpdateCustomerScreen: (customerId: String) -> Unit
) {

    var customer by remember {
        mutableStateOf(Customer(0, NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE))
    }
    BasicLazyListScreenColumn {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp)
        ) {
            items(
                items = customers
            ) { currentCustomer ->
                CustomerCard(
                    customer = currentCustomer,
                    currentCustomer = { _customer ->
                        openDialog()
                        customer = _customer
                    },
                    navigateToUpdateCustomerScreen = navigateToUpdateCustomerScreen
                )

                DeleteAlertDialog(
                    dialogTitle = "Delete Customer",
                    dialogMessage = "Are you sure you want to permanently delete ${customer.customerName}?",
                    openDialog = dialog,
                    delete = { deleteCustomer(customer) },
                    closeDialog = { closeDialog() })
            }

        }

    }
}