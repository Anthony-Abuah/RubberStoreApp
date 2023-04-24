package com.example.rubberstoreapp.feature_app.presentation.composables.customer.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.feature_app.presentation.composables.customer.UpdateCustomerContent
import com.example.rubberstoreapp.feature_app.presentation.composables.customer.UpdateCustomerTopBar
import com.example.rubberstoreapp.feature_app.presentation.view_model.CustomerViewModel


@Composable
fun UpdateCustomerScreen (
    customerViewModel: CustomerViewModel = hiltViewModel(),
    customerId: Int,
    navigateBackToCustomerListScreen: () -> Unit
){

    LaunchedEffect(Unit) {
        customerViewModel.getCustomerByCustomerId(customerId)
    }

    Scaffold(
        topBar = {
            UpdateCustomerTopBar(
                navigateBack = navigateBackToCustomerListScreen
            )
        },
        content = { padding ->
            UpdateCustomerContent(
                padding = padding,
                customer = customerViewModel.customerByCustomerId,
                updateCustomer = { id, name, contact, location, info ->
                    customerViewModel.updateCustomer(id,name,contact,location,info)
                },
                updateCustomerName = {name->
                    customerViewModel.updateCustomerName(name) },
                updateCustomerContact = {contact->
                    customerViewModel.updateCustomerContact(contact) },
                updateCustomerLocation = {location->
                    customerViewModel.updateCustomerLocation(location) },
                updateCustomerInfo = {info->
                    customerViewModel.updateCustomerInfo(info) },
                navigateBack = navigateBackToCustomerListScreen
            )
        }
    )
}