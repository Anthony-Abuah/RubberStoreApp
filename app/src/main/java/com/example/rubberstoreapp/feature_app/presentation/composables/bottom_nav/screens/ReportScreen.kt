package com.example.rubberstoreapp.feature_app.presentation.composables.bottom_nav.screens

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
import com.example.rubberstoreapp.feature_app.presentation.composables.bottom_nav.ReportScreenContent
import com.example.rubberstoreapp.feature_app.presentation.composables.components.AppTopBar
import com.example.rubberstoreapp.feature_app.presentation.view_model.*
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ReportScreen(
    navController: NavHostController
) {
    val customerViewModel: CustomerViewModel = hiltViewModel()
    val itemViewModel: ItemViewModel = hiltViewModel()
    val supplierViewModel: SupplierViewModel = hiltViewModel()
    val bankViewModel: BankViewModel = hiltViewModel()
    val debtViewModel: DebtViewModel = hiltViewModel()
    val debtRepaymentViewModel: DebtRepaymentViewModel = hiltViewModel()
    val savingViewModel: SavingViewModel = hiltViewModel()
    val expenseViewModel: ExpenseViewModel = hiltViewModel()
    val revenueViewModel: RevenueViewModel = hiltViewModel()

    val dayOfMonth = LocalDate.now().dayOfMonth - 1
    val dateOfCurrentMonth =
        java.sql.Date.valueOf(LocalDate.now().minusDays(dayOfMonth.toLong()).toString())
    val currentDate = java.sql.Date.valueOf(LocalDate.now().toString())

    LaunchedEffect(Unit) {
        debtViewModel.getSumOfAllDebts()
        debtViewModel.getTotalSumOfDebtsBetweenDates(dateOfCurrentMonth, currentDate)

        debtRepaymentViewModel.getTotalSumOfDebtRepayment()
        debtRepaymentViewModel.getSumOfDebtRepaymentBetweenDates(dateOfCurrentMonth, currentDate)

        savingViewModel.getTotalSumOfAllSavings()
        savingViewModel.getSumOfSavingsBetweenDates(dateOfCurrentMonth, currentDate)

        expenseViewModel.getTotalSumOfAllExpenses()
        expenseViewModel.getSumOfExpensesBetweenDates(dateOfCurrentMonth, currentDate)

        revenueViewModel.getTotalSumOfAllRevenue()
        revenueViewModel.getSumOfRevenueBetweenDates(dateOfCurrentMonth, currentDate)
    }


    val customers by customerViewModel.customers.collectAsState(
        initial = emptyList()
    )
    val suppliers by supplierViewModel.suppliers.collectAsState(
        initial = emptyList()
    )
    val banks by bankViewModel.allBanks.collectAsState(
        initial = emptyList()
    )
    val items by itemViewModel.items.collectAsState(
        initial = emptyList()
    )
    val debt = debtViewModel.totalSumOfAllDebts
    val monthlyDebt = debtViewModel.totalSumOfDebtsBetweenDates

    val debtPayment = debtRepaymentViewModel.totalSumOfDebtRepayment ?: 0.0
    val monthlyDebtPayment = debtRepaymentViewModel.sumOfDebtRepaymentBetweenDates ?: 0.0

    val revenue = revenueViewModel.totalSumOfAllRevenue
    val monthlyRevenue = revenueViewModel.sumOfRevenueBetweenDates

    val expenses = expenseViewModel.totalSumOfAllExpenses
    val monthlyExpenses = expenseViewModel.sumOfExpensesBetweenDates

    val savings = savingViewModel.totalSumOfAllSavings ?: 0.0
    val monthlySavings = savingViewModel.sumOfSavingsBetweenDates

    Scaffold(
        topBar = {
            AppTopBar(label = "Report")
        },
        bottomBar = {
            BottomBar(navHostController = navController)
        },
        content = { padding ->
            val pad = padding
            ReportScreenContent(
                navController,
                customers,
                suppliers,
                items,
                banks,
                debt,
                debtPayment,
                revenue,
                expenses,
                savings,
                monthlyDebt,
                monthlyDebtPayment,
                monthlyRevenue,
                monthlyExpenses,
                monthlySavings
            )
        }
    )
}