package com.example.rubberstoreapp.feature_app.presentation.composables.bank

sealed class BankScreens(val route: String){
    object BankListScreen: BankScreens("to_bank_list_screen")
    object AddBankScreen: BankScreens("to_add_bank_screen")
    object UpdateBankScreen: BankScreens("to_update_bank_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }
}