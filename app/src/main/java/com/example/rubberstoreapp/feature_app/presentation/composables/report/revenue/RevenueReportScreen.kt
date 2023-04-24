package com.example.rubberstoreapp.feature_app.presentation.composables.report.revenue

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.rubberstoreapp.feature_app.presentation.composables.bottom_nav.BottomBar
import com.example.rubberstoreapp.feature_app.presentation.composables.components.ScreenTopBar
import com.example.rubberstoreapp.feature_app.presentation.view_model.RevenueViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun RevenueReportScreen(
    mainNavController: NavHostController
) {

    val revenueViewModel: RevenueViewModel = hiltViewModel()

    val dayOfMonth = LocalDate.now().dayOfMonth - 1
    val dateOfCurrentMonth =
        java.sql.Date.valueOf(LocalDate.now().minusDays(dayOfMonth.toLong()).toString())
    val currentDate = java.sql.Date.valueOf(LocalDate.now().toString())

    LaunchedEffect(Unit) {
        revenueViewModel.getAllTimeAverageRevenue()
        revenueViewModel.getAllTimeMaxRevenue()
        revenueViewModel.getAllTimeMinRevenue()
        revenueViewModel.getAllTimeRevenueDays()
        revenueViewModel.getDurationalRevenues(dateOfCurrentMonth, currentDate)
        revenueViewModel.getDurationalMaxRevenue(dateOfCurrentMonth, currentDate)
        revenueViewModel.getDurationalMinRevenue(dateOfCurrentMonth, currentDate)
        revenueViewModel.getDurationalRevenueDays(dateOfCurrentMonth, currentDate)
        revenueViewModel.getDurationalAverageRevenue(dateOfCurrentMonth, currentDate)
    }


    val allTimeRevenues by revenueViewModel.allRevenues.collectAsState(
        initial = emptyList()
    )

    val durationalRevenues by revenueViewModel.durationalRevenues.collectAsState(
        initial = emptyList()
    )

    val allTimeMinRevenue = revenueViewModel.allTimeMinRevenue
    val allTimeMaxRevenue = revenueViewModel.allTimeMaxRevenue
    val allTimeAverageRevenue = revenueViewModel.allTimeAverageRevenue
    val allTimeRevenueDays = revenueViewModel.allTimeRevenueDays
    val durationalMinRevenue = revenueViewModel.durationalMinRevenue
    val durationalMaxRevenue = revenueViewModel.durationalMaxRevenue
    val durationalAverageRevenue = revenueViewModel.durationalAverageRevenue
    val durationalRevenueDays = revenueViewModel.durationalRevenueDays


    Scaffold(
        topBar = {
            ScreenTopBar(label = "Revenue Report"){
                mainNavController.popBackStack()
            }
        },
        bottomBar = {
            BottomBar(navHostController = mainNavController)
        },
        content = { padding ->
            val pad = padding
            RevenueReportScreenContent(
                allTimeRevenues, durationalRevenues, allTimeMinRevenue, allTimeMaxRevenue, allTimeAverageRevenue, allTimeRevenueDays, durationalMinRevenue, durationalMaxRevenue, durationalAverageRevenue, durationalRevenueDays
            )
        }
    )
}