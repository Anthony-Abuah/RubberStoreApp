package com.example.rubberstoreapp.feature_app.presentation.composables.customer.screens


import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rubberstoreapp.feature_app.presentation.composables.components.AddFloatingActionButton
import com.example.rubberstoreapp.feature_app.presentation.composables.components.AppTopBar
import com.example.rubberstoreapp.feature_app.presentation.composables.customer.CustomerContent
import com.example.rubberstoreapp.feature_app.presentation.composables.customer.CustomerScreens
import com.example.rubberstoreapp.feature_app.presentation.view_model.CustomerViewModel


@Composable
@ExperimentalMaterialApi
fun CustomerListScreen(
    customerViewModel: CustomerViewModel = hiltViewModel(),
    navigateToUpdateCustomerScreen: (customerId: String) -> Unit,
    navController: NavController
) {
    val customers by customerViewModel.customers.collectAsState(
        initial = emptyList()
    )

    Scaffold(
        topBar = {
            AppTopBar(label = "Customers")
        },
        content = { _padding ->
            val padding = _padding
            CustomerContent(
                dialog = customerViewModel.openDialog,
                customers = customers,
                openDialog = { customerViewModel.openDialog() },
                closeDialog = { customerViewModel.closeDialog() },
                deleteCustomer = { customer ->
                    customerViewModel.deleteCustomer(customer)
                },
                navigateToUpdateCustomerScreen = navigateToUpdateCustomerScreen
            )

        },
        floatingActionButton = {
            AddFloatingActionButton(
                navigateToAnotherScreen =
                {
                    navController.navigate(CustomerScreens.AddCustomerScreen.route)
                },
                description = "Add Customer")
        },
        floatingActionButtonPosition = FabPosition.End
    )
}