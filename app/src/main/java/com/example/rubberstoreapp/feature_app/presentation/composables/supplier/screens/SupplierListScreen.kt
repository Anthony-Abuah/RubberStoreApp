package com.example.rubberstoreapp.feature_app.presentation.composables.supplier.screens


import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rubberstoreapp.feature_app.presentation.composables.components.AddFloatingActionButton
import com.example.rubberstoreapp.feature_app.presentation.composables.components.AppTopBar
import com.example.rubberstoreapp.feature_app.presentation.composables.components.DeleteAlertDialog
import com.example.rubberstoreapp.feature_app.presentation.composables.supplier.SupplierContent
import com.example.rubberstoreapp.feature_app.presentation.composables.supplier.SupplierScreens
import com.example.rubberstoreapp.feature_app.presentation.view_model.SupplierViewModel


@Composable
@ExperimentalMaterialApi
fun SupplierListScreen(
    supplierViewModel: SupplierViewModel = hiltViewModel(),
    navigateToUpdateSupplierScreen: (supplierId: Int) -> Unit,
    navController: NavController
) {
    val suppliers by supplierViewModel.suppliers.collectAsState(
        initial = emptyList()
    )

    Scaffold(
        topBar = {
            AppTopBar(label = "Suppliers")
        },
        content = { padding ->
            SupplierContent(
                padding = padding,
                suppliers = suppliers,
                dialog = supplierViewModel.openDialog,
                openDialog = { supplierViewModel.openDialog() },
                closeDialog = { supplierViewModel.closeDialog() },
                deleteSupplier = { supplier ->
                    supplierViewModel.deleteSupplier(supplier)
                },
                navigateToUpdateSupplierScreen = navigateToUpdateSupplierScreen
            )
        },
        floatingActionButton = {
            AddFloatingActionButton(
                navigateToAnotherScreen =
                {
                    navController.navigate(SupplierScreens.AddSupplierScreen.route)
                },
                description = "Add Supplier")
        }
    )
}