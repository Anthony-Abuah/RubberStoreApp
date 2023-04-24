package com.example.rubberstoreapp.feature_app.presentation.composables.item

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rubberstoreapp.feature_app.presentation.composables.item.screens.AddItemScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.item.screens.ItemListScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.item.screens.UpdateItemScreen


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemNavGraph(){

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ItemScreens.ItemListScreen.route)
    {
        composable(route = ItemScreens.ItemListScreen.route){
            ItemListScreen(
                navigateToUpdateItemScreen = {itemId->
                    navController.navigate(ItemScreens.UpdateItemScreen.withArgs(itemId.toString()))
                },
                navController  = navController
            )
        }
        composable(route = ItemScreens.AddItemScreen.route){
            AddItemScreen(navigateBackToItemListScreen = {
                navController.navigate(ItemScreens.ItemListScreen.route){
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
        }
        composable(route = ItemScreens.UpdateItemScreen.route + "/{itemId}",
            arguments = listOf(
                navArgument("itemId"){
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                })){ entry->
            val itemId = entry.arguments?.getInt("itemId")
            if (itemId != null) {
                UpdateItemScreen(
                    itemId = itemId
                ) {
                    navController.popBackStack()
                }
            }

        }
    }

}