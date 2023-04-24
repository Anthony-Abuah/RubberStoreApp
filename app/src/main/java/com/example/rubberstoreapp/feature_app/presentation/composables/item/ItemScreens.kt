package com.example.rubberstoreapp.feature_app.presentation.composables.item

sealed class ItemScreens(val route: String){
    object ItemListScreen: ItemScreens("to_item_list_screen")
    object AddItemScreen: ItemScreens("to_add_item_screen")
    object UpdateItemScreen: ItemScreens("to_update_item_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }
}