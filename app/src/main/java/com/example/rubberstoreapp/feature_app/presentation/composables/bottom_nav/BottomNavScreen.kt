package com.example.rubberstoreapp.feature_app.presentation.composables.bottom_nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavScreen(
    val name: String,
    val route: String,
    val icon: ImageVector,
){
    object Home: BottomNavScreen(
        name = "Home",
        route = "home",
        icon = Icons.Default.Home
    )
    object Report: BottomNavScreen(
        name = "Report",
        route = "report",
        icon = Icons.Default.Person
    )
    object Settings: BottomNavScreen(
        name = "Settings",
        route = "settings",
        icon = Icons.Default.Settings
    )
}