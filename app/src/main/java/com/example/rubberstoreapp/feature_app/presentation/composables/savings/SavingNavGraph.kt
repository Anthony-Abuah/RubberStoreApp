package com.example.rubberstoreapp.feature_app.presentation.composables.savings

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rubberstoreapp.feature_app.presentation.composables.bank.BankScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.savings.screens.AddSavingScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.savings.screens.SavingListScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.savings.screens.UpdateSavingScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SavingNavGraph(mainNavController: NavHostController) {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = SavingScreens.SavingListScreen.route
    )
    {
        composable(route = SavingScreens.SavingListScreen.route) {
            SavingListScreen(
                navigateToUpdateSavingScreen = { savingsId ->
                    navController.navigate(SavingScreens.UpdateSavingScreen.withArgs(savingsId.toString()))
                },
                navController = navController
            )
        }
        composable(route = SavingScreens.AddSavingScreen.route) {
            AddSavingScreen(
                navigateBackToSavingListScreen = {
                    navController.popBackStack()
                },
                navigateToAddBankScreen = {
                    mainNavController.navigate(BankScreens.AddBankScreen.route)
                }
            )
        }
        composable(
            route = SavingScreens.UpdateSavingScreen.route + "/{savingId}",
            arguments = listOf(
                navArgument("savingId") {
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                })
        ) { entry ->
            val savingId = entry.arguments?.getInt("savingId")
            if (savingId != null) {
                UpdateSavingScreen(
                    savingId = savingId,
                    navigateToAddBankScreen = {
                        mainNavController.navigate(BankScreens.AddBankScreen.route)
                    },
                    navigateBackToSavingListScreen = {
                        navController.popBackStack()
                    }
                )
            }
        }

    }
}