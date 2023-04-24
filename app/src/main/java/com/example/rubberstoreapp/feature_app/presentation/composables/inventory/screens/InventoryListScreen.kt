package com.example.rubberstoreapp.feature_app.presentation.composables.inventory.screens


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
import com.example.rubberstoreapp.feature_app.presentation.composables.inventory.InventoryContent
import com.example.rubberstoreapp.feature_app.presentation.composables.inventory.InventoryScreens
import com.example.rubberstoreapp.feature_app.presentation.view_model.InventoryViewModel


@Composable
@ExperimentalMaterialApi
fun InventoryListScreen(
    inventoryViewModel: InventoryViewModel = hiltViewModel(),
    navigateToUpdateInventoryScreen: (inventoryId: Int) -> Unit,
    navController: NavController
) {
    val inventories by inventoryViewModel.allInventories.collectAsState(
        initial = emptyList()
    )

    Scaffold(
        topBar = {
            AppTopBar(label = "Inventories")
        },
        content = { padding ->
            InventoryContent(
                padding = padding,
                inventories = inventories,
                dialog = inventoryViewModel.openDialog,
                openDialog = { inventoryViewModel.openDialog() },
                closeDialog = { inventoryViewModel.closeDialog() },
                deleteInventory = { inventory ->
                    inventoryViewModel.deleteInventory(inventory)
                },
                navigateToUpdateInventoryScreen = navigateToUpdateInventoryScreen
            )
        },
        floatingActionButton = {
            AddFloatingActionButton(
                navigateToAnotherScreen =
                {
                    navController.navigate(InventoryScreens.AddInventoryScreen.route)
                },
                description = "Add Inventory")
        }
    )
}