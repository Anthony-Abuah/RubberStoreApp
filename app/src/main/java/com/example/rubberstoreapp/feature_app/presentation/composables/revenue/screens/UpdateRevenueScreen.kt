package com.example.rubberstoreapp.feature_app.presentation.composables.revenue.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.feature_app.presentation.composables.revenue.UpdateRevenueContent
import com.example.rubberstoreapp.feature_app.presentation.composables.revenue.UpdateRevenueTopBar
import com.example.rubberstoreapp.feature_app.presentation.view_model.RevenueViewModel


@Composable
fun UpdateRevenueScreen (
    revenueViewModel: RevenueViewModel = hiltViewModel(),
    revenueId: Int,
    navigateBackToRevenueListScreen: () -> Unit
){
    LaunchedEffect(Unit) {
        revenueViewModel.getRevenue(revenueId)
    }

    Scaffold(
        topBar = {
            UpdateRevenueTopBar(
                navigateBack = navigateBackToRevenueListScreen
            )
        },
        content = {padding ->
            UpdateRevenueContent(
                paddingValues = padding,
                revenue = revenueViewModel.revenue,
                updateRevenue = {revenue->
                    revenueViewModel.updateRevenue(revenue)
                },
                updateRevenueDate = {date ->
                    revenueViewModel.updateRevenueDate(date)
                },
                updateAmount = {amount ->
                    revenueViewModel.updateRevenueAmount(amount)
                },
                navigateBack =  navigateBackToRevenueListScreen
            )
        }
    )
}