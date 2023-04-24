package com.example.rubberstoreapp.feature_app.presentation.composables.debt_repayment

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
import com.example.rubberstoreapp.feature_app.presentation.composables.debt_repayment.screens.AddDebtRepaymentScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.debt_repayment.screens.DebtRepaymentListScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.debt_repayment.screens.UpdateDebtRepaymentScreen


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DebtRepaymentNavGraph(mainNavController: NavHostController){

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = DebtRepaymentScreens.DebtRepaymentListScreen.route)
    {
        composable(route = DebtRepaymentScreens.DebtRepaymentListScreen.route){
            DebtRepaymentListScreen(
                navigateToUpdateDebtRepaymentScreen = {debtId->
                    navController.navigate(DebtRepaymentScreens.UpdateDebtRepaymentScreen.withArgs(debtId.toString()))
                },
                navController  = navController
            )
        }
        composable(route = DebtRepaymentScreens.AddDebtRepaymentScreen.route){
            AddDebtRepaymentScreen(navigateBackToDebtRepaymentListScreen = {
                   navController.popBackStack()
                /*navController.navigate(DebtRepaymentScreens.DebtRepaymentListScreen.route){
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }*/
            },
                navigateToAddCustomerScreen = {
                    mainNavController.navigate(CustomerScreens.AddCustomerScreen.route)
                })
        }
        composable(
            route = DebtRepaymentScreens.UpdateDebtRepaymentScreen.route + "/{debtRepaymentId}",
            arguments = listOf(
                navArgument("debtRepaymentId"){
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                })){ entry->
            val debtRepaymentId = entry.arguments?.getInt("debtRepaymentId")
            if (debtRepaymentId != null) {
                UpdateDebtRepaymentScreen(
                    debtRepaymentId = debtRepaymentId,
                    navigateToAddCustomerScreen = {
                        mainNavController.navigate(CustomerScreens.AddCustomerScreen.route)
                    },
                    navigateBackToDebtRepaymentListScreen = {
                        navController.popBackStack()
                    }
                )
            }

        }
    }

}