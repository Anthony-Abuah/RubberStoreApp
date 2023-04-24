package com.example.rubberstoreapp.feature_app.presentation.composables.report.savings

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
import com.example.rubberstoreapp.feature_app.presentation.view_model.SavingViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun SavingReportScreen(
    mainNavController: NavHostController
) {

    val savingsViewModel: SavingViewModel = hiltViewModel()

    val dayOfMonth = LocalDate.now().dayOfMonth - 1
    val dateOfCurrentMonth =
        java.sql.Date.valueOf(LocalDate.now().minusDays(dayOfMonth.toLong()).toString())
    val currentDate = java.sql.Date.valueOf(LocalDate.now().toString())

    LaunchedEffect(Unit) {
        savingsViewModel.getAllTimeAverageSavings()
        savingsViewModel.getAllTimeMaximumSavings()
        savingsViewModel.getAllTimeMinimumSavings()
        savingsViewModel.getAllTimeNumberOfSavings()
        savingsViewModel.getDurationalSavings(dateOfCurrentMonth, currentDate)
        savingsViewModel.getDurationalAverageSavings(dateOfCurrentMonth, currentDate)
        savingsViewModel.getDurationalMinimumSavings(dateOfCurrentMonth, currentDate)
        savingsViewModel.getDurationalMaximumSavings(dateOfCurrentMonth, currentDate)
        savingsViewModel.getDurationalNumberOfSavings(dateOfCurrentMonth, currentDate)
    }


    val allTimeSavings by savingsViewModel.allSavings.collectAsState(
        initial = emptyList()
    )

    val durationalSavings by savingsViewModel.durationalSavings.collectAsState(
        initial = emptyList()
    )

    val allTimeMinimumSavings = savingsViewModel.allTimeMinimumSavings
    val allTimeMaximumSavings = savingsViewModel.allTimeMaximumSavings
    val allTimeAverageSavings = savingsViewModel.allTimeAverageSavings
    val allTimeNumberOfSavings = savingsViewModel.allTimeNumberOfSavings
    val durationalMinimumSavings = savingsViewModel.durationalMinimumSavings
    val durationalMaximumSavings = savingsViewModel.durationalMaximumSavings
    val durationalAverageSavings = savingsViewModel.durationalAverageSavings
    val durationalNumberOfSavings = savingsViewModel.durationalNumberOfSavings


    Scaffold(
        topBar = {
            ScreenTopBar(label = "Saving Report"){
                mainNavController.popBackStack()
            }
        },
        bottomBar = {
            BottomBar(navHostController = mainNavController)
        },
        content = { padding ->
            val pad = padding
            SavingsReportScreenContent(
                allTimeSavings, durationalSavings, allTimeMinimumSavings, allTimeMaximumSavings, allTimeAverageSavings, allTimeNumberOfSavings, durationalMinimumSavings, durationalMaximumSavings, durationalAverageSavings, durationalNumberOfSavings
            )
        }
    )
}