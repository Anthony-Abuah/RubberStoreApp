package com.example.rubberstoreapp.feature_app.presentation.composables.customer

sealed class CustomerScreens(val route: String){
    object CustomerListScreen: CustomerScreens("to_customer_list_screen")
    object AddCustomerScreen: CustomerScreens("to_add_customer_screen")
    object UpdateCustomerScreen: CustomerScreens("to_update_customer_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }
}