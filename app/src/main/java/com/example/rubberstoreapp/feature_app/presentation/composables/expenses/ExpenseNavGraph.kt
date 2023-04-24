package com.example.rubberstoreapp.feature_app.presentation.composables.expenses

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rubberstoreapp.feature_app.presentation.composables.expenses.screens.AddExpenseScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.expenses.screens.ExpenseListScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.expenses.screens.UpdateExpenseScreen


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpenseNavGraph(){

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ExpenseScreens.ExpenseListScreen.route)
    {
        composable(route = ExpenseScreens.ExpenseListScreen.route){
            ExpenseListScreen(
                navigateToUpdateExpenseScreen = {expenseId->
                    navController.navigate(ExpenseScreens.UpdateExpenseScreen.withArgs(expenseId.toString()))
                },
                navController  = navController
            )
        }
        composable(route = ExpenseScreens.AddExpenseScreen.route){
            AddExpenseScreen(navigateBackToExpenseListScreen = {
                navController.navigate(ExpenseScreens.ExpenseListScreen.route){
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
        }
        composable(route = ExpenseScreens.UpdateExpenseScreen.route + "/{expenseId}",
            arguments = listOf(
                navArgument("expenseId"){
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                })){ entry->
            val expenseId = entry.arguments?.getInt("expenseId")
            if (expenseId != null) {
                UpdateExpenseScreen(
                    expenseId = expenseId
                ) {
                    navController.popBackStack()
                }
            }

        }
    }

}