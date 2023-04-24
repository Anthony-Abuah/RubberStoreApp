package com.example.rubberstoreapp.feature_app.presentation.composables.bank

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rubberstoreapp.feature_app.presentation.composables.bank.screens.AddBankScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.bank.screens.BankListScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.bank.screens.UpdateBankScreen


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BankNavGraph(){

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = BankScreens.BankListScreen.route)
    {
        composable(route = BankScreens.BankListScreen.route){
            BankListScreen(
                navigateToUpdateBankScreen = {bankId->
                    navController.navigate(BankScreens.UpdateBankScreen.withArgs(bankId.toString()))
                },
                navController  = navController
            )
        }
        composable(route = BankScreens.AddBankScreen.route){
            AddBankScreen(navigateBackToBankListScreen = {
                navController.navigate(BankScreens.BankListScreen.route){
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
        }
        
        composable(route = BankScreens.UpdateBankScreen.route + "/{bankId}",
            arguments = listOf(
                navArgument("bankId"){
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                })){ entry->
            val bankId = entry.arguments?.getInt("bankId")
            if (bankId != null) {
                UpdateBankScreen(
                    bankId = bankId
                ) {
                    navController.popBackStack()
                }
            }

        }

    }

}