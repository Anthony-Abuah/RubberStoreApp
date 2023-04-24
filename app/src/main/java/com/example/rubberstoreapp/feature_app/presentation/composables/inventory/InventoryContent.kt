package com.example.rubberstoreapp.feature_app.presentation.composables.inventory

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rubberstoreapp.feature_app.data.local.inventory.Inventory
import com.example.rubberstoreapp.feature_app.domain.repository.Inventories
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicLazyListScreenColumn
import com.example.rubberstoreapp.feature_app.presentation.composables.components.DeleteAlertDialog
import java.util.*

@Composable
@ExperimentalMaterialApi
fun InventoryContent(
    padding: PaddingValues,
    inventories: Inventories,
    dialog: Boolean,
    openDialog: () -> Unit,
    closeDialog: () -> Unit,
    deleteInventory: (inventory: Inventory) -> Unit,
    navigateToUpdateInventoryScreen: (inventoryId: Int) -> Unit
) {
    var inventory by remember {
        mutableStateOf(Inventory(0, Date(), emptyList(), 0.0))
    }

    BasicLazyListScreenColumn {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp)
        ) {
            items(
                items = inventories
            ) { theCurrentInventory ->
                InventoryCard(
                    inventory = theCurrentInventory,
                    currentInventory = { currentInventory ->
                        openDialog()
                        inventory = currentInventory
                    },
                    navigateToUpdateInventoryScreen = navigateToUpdateInventoryScreen
                )
            }
        }

        DeleteAlertDialog(
            dialogTitle = "Delete Inventory",
            dialogMessage = "Are you sure you want to permanently delete inventory?",
            openDialog = dialog,
            delete = { deleteInventory(inventory) },
            closeDialog = closeDialog
        )
    }
}