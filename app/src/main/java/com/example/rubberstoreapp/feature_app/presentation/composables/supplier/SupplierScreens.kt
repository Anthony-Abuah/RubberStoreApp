package com.example.rubberstoreapp.feature_app.presentation.composables.supplier

sealed class SupplierScreens(val route: String){
    object SupplierListScreen: SupplierScreens("to_supplier_list_screen")
    object AddSupplierScreen: SupplierScreens("to_add_supplier_screen")
    object UpdateSupplierScreen: SupplierScreens("to_update_supplier_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }
}