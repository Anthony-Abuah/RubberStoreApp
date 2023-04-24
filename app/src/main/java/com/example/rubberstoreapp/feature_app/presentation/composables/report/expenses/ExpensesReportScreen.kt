package com.example.rubberstoreapp.feature_app.presentation.composables.report.expenses

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
import com.example.rubberstoreapp.feature_app.presentation.view_model.ExpenseViewModel
import com.example.rubberstoreapp.feature_app.presentation.view_model.InventoryViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ExpensesReportScreen(
    mainNavController: NavHostController
) {

    val expensesViewModel: ExpenseViewModel = hiltViewModel()
    val inventoryViewModel: InventoryViewModel = hiltViewModel()

    val dayOfMonth = LocalDate.now().dayOfMonth - 1
    val dateOfCurrentMonth =
        java.sql.Date.valueOf(LocalDate.now().minusDays(dayOfMonth.toLong()).toString())
    val currentDate = java.sql.Date.valueOf(LocalDate.now().toString())

    LaunchedEffect(Unit) {
        expensesViewModel.getTotalSumOfAllExpenses()
        expensesViewModel.getAllTimeExpenseNameValues()
        expensesViewModel.getAllTimeExpenseDateValue()
        inventoryViewModel.getAllTimeTotalCost()
        inventoryViewModel.getAllTimeInventoryDateCosts()
        expensesViewModel.getDurationalExpenseDateValue(dateOfCurrentMonth, currentDate)
        expensesViewModel.getDurationalExpenseNameValues(dateOfCurrentMonth, currentDate)
        expensesViewModel.getSumOfExpensesBetweenDates(dateOfCurrentMonth, currentDate)
        inventoryViewModel.getDurationalTotalCost(dateOfCurrentMonth, currentDate)
        inventoryViewModel.getDurationalInventoryDateCosts(dateOfCurrentMonth, currentDate)
    }


    val allTimeExpenseNameValues by expensesViewModel.allTimeExpenseNameValues.collectAsState(
        initial = emptyList()
    )
    val durationalExpenseNameValues by expensesViewModel.durationalExpenseNameValues.collectAsState(
        initial = emptyList()
    )
    val allTimeExpenseDateValues by expensesViewModel.allTimeExpenseDateValue.collectAsState(
        initial = emptyList()
    )

    val durationalExpenseDateValues by expensesViewModel.durationalExpenseDateValue.collectAsState(
        initial = emptyList()
    )

    val allTimeInventoryDateCosts by inventoryViewModel.allTimeInventoryDateCosts.collectAsState(
        initial = emptyList()
    )

    val durationalInventoryDateCosts by inventoryViewModel.durationalInventoryDateCosts.collectAsState(
        initial = emptyList()
    )

    val allTimeOtherExpenses = expensesViewModel.totalSumOfAllExpenses
    val allTimeInventoryCost = inventoryViewModel.allTimeTotalCost
    val durationalOtherExpenses = expensesViewModel.sumOfExpensesBetweenDates
    val durationalInventoryCost = inventoryViewModel.durationalTotalCost

    Scaffold(
        topBar = {
            ScreenTopBar(label = "Expense Report"){
                mainNavController.popBackStack()
            }
        },
        bottomBar = {
            BottomBar(navHostController = mainNavController)
        },
        content = { padding ->
            val pad = padding
            ExpensesReportScreenContent(
                allTimeExpenseNameValues, durationalExpenseNameValues, allTimeExpenseDateValues, durationalExpenseDateValues, allTimeInventoryDateCosts, durationalInventoryDateCosts, allTimeOtherExpenses, allTimeInventoryCost, durationalOtherExpenses, durationalInventoryCost
            )
        }
    )
}