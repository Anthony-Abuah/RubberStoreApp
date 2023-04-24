package com.example.rubberstoreapp.feature_app.presentation.composables.revenue

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rubberstoreapp.feature_app.presentation.composables.revenue.screens.AddRevenueScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.revenue.screens.RevenueListScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.revenue.screens.UpdateRevenueScreen


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RevenueNavGraph(){

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = RevenueScreens.RevenueListScreen.route)
    {
        composable(route = RevenueScreens.RevenueListScreen.route){
            RevenueListScreen(
                navigateToUpdateRevenueScreen = {revenueId->
                    navController.navigate(RevenueScreens.UpdateRevenueScreen.withArgs(revenueId.toString()))
                },
                navController  = navController
            )
        }
        composable(route = RevenueScreens.AddRevenueScreen.route){
            AddRevenueScreen(navigateBackToRevenueListScreen = {
                navController.navigate(RevenueScreens.RevenueListScreen.route){
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
        }
        composable(
            route = RevenueScreens.UpdateRevenueScreen.route + "/{revenueId}",
            arguments = listOf(
                navArgument("revenueId"){
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                })){ entry->
            val revenueId = entry.arguments?.getInt("revenueId")
            if (revenueId != null) {
                UpdateRevenueScreen(
                    revenueId = revenueId,
                    navigateBackToRevenueListScreen = {
                        navController.popBackStack()
                    }
                )
            }

        }
        
    }
}