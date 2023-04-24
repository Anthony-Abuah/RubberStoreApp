package com.example.rubberstoreapp.feature_app.presentation.composables.expenses

sealed class ExpenseScreens(val route: String){
    object ExpenseListScreen: ExpenseScreens("to_expense_list_screen")
    object AddExpenseScreen: ExpenseScreens("to_add_expense_screen")
    object UpdateExpenseScreen: ExpenseScreens("to_update_expense_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }
}