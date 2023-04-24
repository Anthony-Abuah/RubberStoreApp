package com.example.rubberstoreapp.feature_app.presentation.composables.inventory.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.feature_app.presentation.composables.inventory.AddInventoryContent
import com.example.rubberstoreapp.feature_app.presentation.composables.inventory.AddInventoryTopBar
import com.example.rubberstoreapp.feature_app.presentation.view_model.InventoryViewModel

@Composable
fun AddInventoryScreen(
    inventoryViewModel: InventoryViewModel = hiltViewModel(),
    navigateBackToInventoryListScreen: () -> Unit,
    ){

    Scaffold(
        topBar = {
            AddInventoryTopBar(
                navigateBack = navigateBackToInventoryListScreen
            )
        },
        content = { padding ->
            AddInventoryContent(
                paddingValues = padding,
                inventoryItems = inventoryViewModel.inventoryItems,
                insertInventory = {inventory ->
                    inventoryViewModel.addInventory(inventory)
                },
                navigateBack = { navigateBackToInventoryListScreen()})
        }
    )
}