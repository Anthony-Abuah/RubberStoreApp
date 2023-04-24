package com.example.rubberstoreapp.feature_app.presentation.composables.inventory

sealed class InventoryScreens(val route: String){
    object InventoryListScreen: InventoryScreens("to_inventory_list_screen")
    object AddInventoryScreen: InventoryScreens("to_add_inventory_screen")
    object UpdateInventoryScreen: InventoryScreens("to_update_inventory_screen")
}