package com.example.rubberstoreapp.feature_app.presentation.composables.inventory

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rubberstoreapp.feature_app.presentation.composables.inventory.screens.AddInventoryScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.inventory.screens.InventoryListScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.inventory.screens.UpdateInventoryScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InventoryNavGraph(){

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = InventoryScreens.InventoryListScreen.route)
    {
        composable(route = InventoryScreens.InventoryListScreen.route){
            InventoryListScreen(
                navigateToUpdateInventoryScreen = {/*TODO*/},
                navController  = navController
            )
        }
        composable(route = InventoryScreens.AddInventoryScreen.route){
            AddInventoryScreen(
                navigateBackToInventoryListScreen = {
                navController.navigate(InventoryScreens.InventoryListScreen.route){
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true }
                }
            )
        }
        composable(route = InventoryScreens.UpdateInventoryScreen.route){
            UpdateInventoryScreen()
        }
    }

}