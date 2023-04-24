package com.example.rubberstoreapp.feature_app.presentation.composables.supplier.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.feature_app.presentation.composables.supplier.AddSupplierContent
import com.example.rubberstoreapp.feature_app.presentation.composables.supplier.AddSupplierTopBar
import com.example.rubberstoreapp.feature_app.presentation.view_model.SupplierViewModel

@Composable
fun AddSupplierScreen(
    supplierViewModel: SupplierViewModel = hiltViewModel(),
    navigateBackToSupplierListScreen: () -> Unit
    ){

    Scaffold(
        topBar = {
            AddSupplierTopBar(
                navigateBack = navigateBackToSupplierListScreen
            )
        },
        content = { padding ->
            AddSupplierContent(
                padding = padding,
                insertSupplier = { supplier ->
                    supplierViewModel.addSupplier(supplier)
                },
                navigateBack = navigateBackToSupplierListScreen
            )
        }
    )
}