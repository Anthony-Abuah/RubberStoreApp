package com.example.rubberstoreapp.feature_app.presentation.composables.revenue.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.feature_app.presentation.composables.revenue.AddRevenueContent
import com.example.rubberstoreapp.feature_app.presentation.composables.revenue.AddRevenueTopBar
import com.example.rubberstoreapp.feature_app.presentation.view_model.RevenueViewModel

@Composable
fun AddRevenueScreen(
    revenueViewModel: RevenueViewModel = hiltViewModel(),
    navigateBackToRevenueListScreen: () -> Unit
    ){

    val revenues by revenueViewModel.allRevenues.collectAsState(
        initial = emptyList()
    )


    Scaffold(
        topBar = {
            AddRevenueTopBar(
                navigateBack = navigateBackToRevenueListScreen
            )
        },
        content = { padding ->
            AddRevenueContent(
                paddingValues = padding,
                revenues = revenues,
                insertRevenue = { revenue ->
                    revenueViewModel.addRevenue(revenue)
                },
                navigateBack = navigateBackToRevenueListScreen
            )
        }
    )
}