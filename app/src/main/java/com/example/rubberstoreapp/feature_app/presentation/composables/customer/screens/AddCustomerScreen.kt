package com.example.rubberstoreapp.feature_app.presentation.composables.customer.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.feature_app.presentation.composables.customer.AddCustomerContent
import com.example.rubberstoreapp.feature_app.presentation.composables.customer.AddCustomerTopBar
import com.example.rubberstoreapp.feature_app.presentation.view_model.CustomerViewModel

@Composable
fun AddCustomerScreen(
    customerViewModel: CustomerViewModel = hiltViewModel(),
    navigateBackToCustomerListScreen: () -> Unit
    ){

    Scaffold(
        topBar = {
            AddCustomerTopBar(
                navigateBack = navigateBackToCustomerListScreen
            )
        },
        content = { padding ->
            AddCustomerContent(
                padding = padding,
                insertCustomer = { customer ->
                    customerViewModel.addCustomer(customer)
                },
                navigateBack = navigateBackToCustomerListScreen
            )
        }
    )
}