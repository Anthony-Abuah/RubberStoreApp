package com.example.rubberstoreapp.feature_app.presentation.composables.bottom_nav

import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(
    navHostController: NavHostController
){
    val screens = listOf(
        BottomNavScreen.Home,
        BottomNavScreen.Report,
        BottomNavScreen.Settings,
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach{screen->
            AddItem(screen = screen, currentDestination = currentDestination, navController = navHostController)
        }
    }
}