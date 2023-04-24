package com.example.rubberstoreapp.feature_app.presentation.composables.debt

sealed class DebtScreens(val route: String){
    object DebtListScreen: DebtScreens("to_debt_list_screen")
    object AddDebtScreen: DebtScreens("to_add_debt_screen")
    object UpdateDebtScreen: DebtScreens("to_update_debt_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }
}