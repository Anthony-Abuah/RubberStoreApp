package com.example.rubberstoreapp.feature_app.presentation.composables.savings

sealed class SavingScreens(val route: String){
    object SavingListScreen: SavingScreens("to_saving_list_screen")
    object AddSavingScreen: SavingScreens("to_add_saving_screen")
    object UpdateSavingScreen: SavingScreens("to_update_saving_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }
}