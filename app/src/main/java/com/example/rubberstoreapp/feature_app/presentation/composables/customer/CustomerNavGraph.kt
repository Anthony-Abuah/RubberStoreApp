package com.example.rubberstoreapp.feature_app.presentation.composables.customer

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rubberstoreapp.feature_app.presentation.composables.customer.screens.AddCustomerScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.customer.screens.CustomerListScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.customer.screens.UpdateCustomerScreen


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomerNavGraph(
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = CustomerScreens.CustomerListScreen.route)
    {
        composable(route = CustomerScreens.CustomerListScreen.route){
            CustomerListScreen(
                navigateToUpdateCustomerScreen = {customerId->
                    navController.navigate(CustomerScreens.UpdateCustomerScreen.withArgs(customerId))},
                navController  = navController
            )
        }
        composable(route = CustomerScreens.AddCustomerScreen.route){
            AddCustomerScreen(navigateBackToCustomerListScreen = {
                //navController.popBackStack()
                navController.navigate(CustomerScreens.CustomerListScreen.route){
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
        }
        composable(
            route = CustomerScreens.UpdateCustomerScreen.route + "/{customerId}",
            arguments = listOf(
                navArgument("customerId"){
                    type = NavType.StringType
                    defaultValue = "0"
                    nullable = true
                }
            )
        ){entry ->
            val customerId = entry.arguments?.getString("customerId")?.toInt()
            if (customerId != null) {
                UpdateCustomerScreen(
                    customerId = customerId,
                    navigateBackToCustomerListScreen = {
                        navController.popBackStack()
                    }
                )
            }


        }
    }

}