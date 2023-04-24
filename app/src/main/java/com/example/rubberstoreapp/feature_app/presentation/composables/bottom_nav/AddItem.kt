package com.example.rubberstoreapp.feature_app.presentation.composables.bottom_nav

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

@Composable
fun RowScope.AddItem(
    screen: BottomNavScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
){


        BottomNavigationItem(
            label = {
                Text(text = screen.name)
            },
            icon = {
                Icon(imageVector = screen.icon,
                    contentDescription = "Navigation Icon" )
            },
            selected = currentDestination?.hierarchy?.any{
                it.route == screen.route
            } == true,

            unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),

            onClick = {
                navController.navigate(screen.route){
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    }
