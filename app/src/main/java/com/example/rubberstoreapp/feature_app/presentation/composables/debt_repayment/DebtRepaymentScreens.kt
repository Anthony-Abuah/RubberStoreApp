package com.example.rubberstoreapp.feature_app.presentation.composables.debt_repayment

sealed class DebtRepaymentScreens(val route: String){
    object DebtRepaymentListScreen: DebtRepaymentScreens("to_DebtRepayment_list_screen")
    object AddDebtRepaymentScreen: DebtRepaymentScreens("to_add_DebtRepayment_screen")
    object UpdateDebtRepaymentScreen: DebtRepaymentScreens("to_update_DebtRepayment_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }
}