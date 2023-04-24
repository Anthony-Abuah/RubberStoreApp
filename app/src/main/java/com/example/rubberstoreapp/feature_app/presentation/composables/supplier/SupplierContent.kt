package com.example.rubberstoreapp.feature_app.presentation.composables.supplier

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.supplier.Supplier
import com.example.rubberstoreapp.feature_app.domain.repository.Suppliers
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicLazyListScreenColumn
import com.example.rubberstoreapp.feature_app.presentation.composables.components.DeleteAlertDialog

@Composable
@ExperimentalMaterialApi
fun SupplierContent(
    padding: PaddingValues,
    suppliers: Suppliers,
    dialog: Boolean,
    openDialog: () -> Unit,
    closeDialog: () -> Unit,
    deleteSupplier: (supplier: Supplier) -> Unit,
    navigateToUpdateSupplierScreen: (supplierId: Int) -> Unit
) {
    var supplier by remember {
        mutableStateOf(Supplier(0, NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE))
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)
    ) {
        items(
            items = suppliers
        ) { theCurrentSupplier ->
            SupplierCard(
                supplier = theCurrentSupplier,
                currentSupplier = { currentSupplier ->
                    openDialog()
                    supplier = currentSupplier
                },
                navigateToUpdateSupplierScreen = navigateToUpdateSupplierScreen
            )
        }
    }

    DeleteAlertDialog(
        dialogTitle = "Delete Supplier",
        dialogMessage = "Are you sure you want to permanently remove ${supplier.supplierName}?",
        openDialog = dialog,
        delete = { deleteSupplier(supplier) },
        closeDialog = closeDialog
    )

}