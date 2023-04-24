package com.example.rubberstoreapp.feature_app.presentation.composables.report.debtors

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
import com.example.rubberstoreapp.feature_app.presentation.view_model.DebtViewModel
import com.example.rubberstoreapp.feature_app.presentation.view_model.DebtRepaymentViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun DebtorsReportScreen(
    mainNavController: NavHostController
) {

    val debtsViewModel: DebtViewModel = hiltViewModel()
    val debtPaymentViewModel: DebtRepaymentViewModel = hiltViewModel()

    val dayOfMonth = LocalDate.now().dayOfMonth - 1
    val dateOfCurrentMonth =
        java.sql.Date.valueOf(LocalDate.now().minusDays(dayOfMonth.toLong()).toString())
    val currentDate = java.sql.Date.valueOf(LocalDate.now().toString())

    LaunchedEffect(Unit) {
        debtsViewModel.getSumOfAllDebts()
        debtPaymentViewModel.getTotalSumOfDebtRepayment()
        debtsViewModel.getAllTimeCustomerNameDebt()
        debtsViewModel.getAllTimeDebtDateValues()
        debtPaymentViewModel.getAllTimeDebtRepaymentDateValues()
        debtPaymentViewModel.getAllTimeCustomerNameDebtRepayment()
        debtsViewModel.getTotalSumOfDebtsBetweenDates(dateOfCurrentMonth, currentDate)
        debtsViewModel.getDurationalDebtDateValues(dateOfCurrentMonth, currentDate)
        debtsViewModel.getDurationalCustomerNameDebt(dateOfCurrentMonth, currentDate)
        debtPaymentViewModel.getDurationalDebtRepaymentDateValues(dateOfCurrentMonth, currentDate)
        debtPaymentViewModel.getDurationalCustomerNameDebtRepayment(dateOfCurrentMonth, currentDate)
        debtPaymentViewModel.getSumOfDebtRepaymentBetweenDates(dateOfCurrentMonth, currentDate)
    }


    val allTimeCustomerNameDebt by debtsViewModel.allTimeCustomerNameDebt.collectAsState(
        initial = emptyList()
    )
    val durationalCustomerNameDebt by debtsViewModel.durationalCustomerNameDebt.collectAsState(
        initial = emptyList()
    )
    val allTimeDebtDateValues by debtsViewModel.allTimeDebtDateValues.collectAsState(
        initial = emptyList()
    )

    val durationalDebtDateValues by debtsViewModel.durationalDebtDateValues.collectAsState(
        initial = emptyList()
    )

    val allTimeDebtRepaymentDateValues by debtPaymentViewModel.allTimeDebtRepaymentDateValues.collectAsState(
        initial = emptyList()
    )

    val durationalDebtRepaymentDateValues by debtPaymentViewModel.durationalDebtRepaymentDateValues.collectAsState(
        initial = emptyList()
    )

    val allTimeCustomerNameDebtRepayment by debtPaymentViewModel.allTimeCustomerNameDebtRepayment.collectAsState(
        initial = emptyList()
    )

    val durationalCustomerNameDebtRepayment by debtPaymentViewModel.durationalCustomerNameDebtRepayment.collectAsState(
        initial = emptyList()
    )

    val allTimeDebtAmount = debtsViewModel.totalSumOfAllDebts
    val durationalDebtAmount = debtsViewModel.totalSumOfDebtsBetweenDates
    val allTimeDebtRepaymentAmount = debtPaymentViewModel.totalSumOfDebtRepayment
    val durationalDebtRepaymentAmount = debtPaymentViewModel.sumOfDebtRepaymentBetweenDates

    Scaffold(
        topBar = {
            ScreenTopBar(label = "Debtors' Report"){
                mainNavController.popBackStack()
            }
        },
        bottomBar = {
            BottomBar(navHostController = mainNavController)
        },
        content = { padding ->
            val pad = padding
            DebtorsReportScreenContent(
                allTimeDebtAmount,durationalDebtAmount,allTimeDebtRepaymentAmount,durationalDebtRepaymentAmount, allTimeCustomerNameDebt, durationalCustomerNameDebt, allTimeCustomerNameDebtRepayment, durationalCustomerNameDebtRepayment, allTimeDebtDateValues, durationalDebtDateValues, allTimeDebtRepaymentDateValues, durationalDebtRepaymentDateValues
            )
        }
    )
}