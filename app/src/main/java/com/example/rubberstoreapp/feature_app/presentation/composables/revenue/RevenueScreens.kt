package com.example.rubberstoreapp.feature_app.presentation.composables.revenue

sealed class RevenueScreens(val route: String){
    object RevenueListScreen: RevenueScreens("to_revenue_list_screen")
    object AddRevenueScreen: RevenueScreens("to_add_revenue_screen")
    object UpdateRevenueScreen: RevenueScreens("to_update_revenue_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }
}