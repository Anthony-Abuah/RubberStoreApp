package com.example.rubberstoreapp.feature_app.presentation.composables.bottom_nav.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.rubberstoreapp.R
import com.example.rubberstoreapp.feature_app.presentation.composables.bottom_nav.BottomBar
import com.example.rubberstoreapp.feature_app.presentation.composables.bottom_nav.HomeScreenContent
import com.example.rubberstoreapp.feature_app.presentation.composables.components.AppTopBar
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicImage


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            AppTopBar(label = "Home")
        },
        bottomBar = {
            BottomBar(navHostController = navController)
        },
        content = { padding ->
            HomeScreenContent(
                navController = navController,
                paddingValues = padding
            )
        }
    )

}