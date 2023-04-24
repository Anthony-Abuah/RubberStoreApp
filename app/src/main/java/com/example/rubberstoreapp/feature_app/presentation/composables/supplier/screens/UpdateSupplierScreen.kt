package com.example.rubberstoreapp.feature_app.presentation.composables.supplier.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.feature_app.presentation.composables.supplier.UpdateSupplierContent
import com.example.rubberstoreapp.feature_app.presentation.composables.supplier.UpdateSupplierTopBar
import com.example.rubberstoreapp.feature_app.presentation.view_model.SupplierViewModel


@Composable
fun UpdateSupplierScreen (
    supplierViewModel: SupplierViewModel = hiltViewModel(),
    supplierId: Int,
    navigateBackToSupplierListScreen: () -> Unit
){

    LaunchedEffect(Unit) {
        supplierViewModel.getSupplier(supplierId)
    }

    Scaffold(
        topBar = {
            UpdateSupplierTopBar(
                navigateBack = navigateBackToSupplierListScreen
            )
        },
        content = { padding ->
            UpdateSupplierContent(
                padding = padding,
                supplier = supplierViewModel.supplier,
                updateSupplier = { supplier->
                    supplierViewModel.updateSupplier(supplier)
                },
                updateSupplierName = {name->
                    supplierViewModel.updateSupplierName(name) },
                updateSupplierContact = {contact->
                    supplierViewModel.updateSupplierContact(contact) },
                updateSupplierLocation = {location->
                    supplierViewModel.updateSupplierLocation(location) },
                updateSupplierInfo = {info->
                    supplierViewModel.updateSupplierInfo(info) },
                navigateBack = navigateBackToSupplierListScreen
            )
        }
    )
}