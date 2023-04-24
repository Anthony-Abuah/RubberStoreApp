package com.example.rubberstoreapp.feature_app.presentation.composables.supplier

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rubberstoreapp.feature_app.presentation.composables.supplier.screens.AddSupplierScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.supplier.screens.SupplierListScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.supplier.screens.UpdateSupplierScreen


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SupplierNavGraph(){

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = SupplierScreens.SupplierListScreen.route)
    {
        composable(route = SupplierScreens.SupplierListScreen.route){
            SupplierListScreen(
                navigateToUpdateSupplierScreen = {supplierId->
                    navController.navigate(SupplierScreens.UpdateSupplierScreen.withArgs(supplierId.toString()))
                },
                navController  = navController
            )
        }
        composable(route = SupplierScreens.AddSupplierScreen.route){
            AddSupplierScreen(navigateBackToSupplierListScreen = {
                navController.navigate(SupplierScreens.SupplierListScreen.route){
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
        }
        composable(
            route = SupplierScreens.UpdateSupplierScreen.route + "/{supplierId}",
            arguments = listOf(
                navArgument("supplierId"){
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                })){entry->
            val supplierId = entry.arguments?.getInt("supplierId")
            if (supplierId != null) {
                UpdateSupplierScreen(
                    supplierId = supplierId
                ) {
                    navController.popBackStack()
                }
            }

        }
    }

}