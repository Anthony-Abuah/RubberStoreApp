package com.example.rubberstoreapp.feature_app.presentation.composables.debt

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rubberstoreapp.feature_app.presentation.composables.customer.CustomerScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.debt.DebtScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.debt.screens.UpdateDebtScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.debt.screens.AddDebtScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.debt.screens.DebtListScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.debt.screens.UpdateDebtScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DebtNavGraph(mainNavController: NavHostController){

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = DebtScreens.DebtListScreen.route)
    {
        composable(route = DebtScreens.DebtListScreen.route){
            DebtListScreen(
                navigateToUpdateDebtScreen = {debtId->
                    navController.navigate(DebtScreens.UpdateDebtScreen.withArgs(debtId.toString()))
                },
                navController  = navController
            )
        }
        composable(route = DebtScreens.AddDebtScreen.route){
            AddDebtScreen(
                navigateBackToDebtListScreen = {
                navController.popBackStack()
                /*navigate(DebtScreens.DebtListScreen.route){
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }*/
            },
                navigateToAddCustomerScreen = {
                    mainNavController.navigate(CustomerScreens.AddCustomerScreen.route)
                }
            )
        }
        composable(
            route = DebtScreens.UpdateDebtScreen.route + "/{debtId}",
            arguments = listOf(
                navArgument("debtId"){
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                })){ entry->
            val debtId = entry.arguments?.getInt("debtId")
            if (debtId != null) {
                UpdateDebtScreen(
                    debtId = debtId,
                    navigateToAddCustomerScreen = {
                        mainNavController.navigate(CustomerScreens.AddCustomerScreen.route)
                    },
                    navigateBackToDebtListScreen = {
                        navController.popBackStack()
                    }
                )
            }
        }

    }
}