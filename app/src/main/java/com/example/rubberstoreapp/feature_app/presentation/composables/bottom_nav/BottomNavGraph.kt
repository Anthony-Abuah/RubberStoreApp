package com.example.rubberstoreapp.feature_app.presentation.composables.bottom_nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rubberstoreapp.feature_app.presentation.*
import com.example.rubberstoreapp.feature_app.presentation.composables.bank.BankScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.bank.screens.AddBankScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.bottom_nav.screens.HomeScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.bottom_nav.screens.ReportScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.bottom_nav.screens.SettingsScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.customer.CustomerScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.customer.screens.AddCustomerScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.debt.DebtScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.debt_repayment.DebtRepaymentScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.expenses.ExpenseScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.inventory.InventoryScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.item.ItemScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.report.debtors.DebtorsReportScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.report.expenses.ExpensesReportScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.report.revenue.RevenueReportScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.report.savings.SavingReportScreen
import com.example.rubberstoreapp.feature_app.presentation.composables.report.screens.ReportScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.revenue.RevenueScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.savings.SavingScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.supplier.SupplierScreens

@Composable
fun BottomNavGraph (mainNavController: NavHostController){

    //val navController = rememberNavController()
    NavHost(
        navController = mainNavController,
        startDestination = BottomNavScreen.Home.route)
    {
        composable(route = BottomNavScreen.Home.route){
            HomeScreen(mainNavController)
        }
        composable(route = BottomNavScreen.Report.route){
            ReportScreen(mainNavController)
        }
        composable(route = BottomNavScreen.Settings.route){
            SettingsScreen()
        }
        composable(route = CustomerScreens.CustomerListScreen.route){
            MainCustomerScreen()
        }
        composable(route = BankScreens.BankListScreen.route){
            MainBankScreen(mainNavController)
        }
        composable(route = DebtScreens.DebtListScreen.route){
            MainDebtScreen(mainNavController)
        }
        composable(route = DebtRepaymentScreens.DebtRepaymentListScreen.route){
            MainDebtRepaymentScreen(mainNavController)
        }
        composable(route = ExpenseScreens.ExpenseListScreen.route){
            MainExpenseScreen(mainNavController)
        }
        composable(route = InventoryScreens.InventoryListScreen.route){
            MainInventoryScreen(mainNavController)
        }
        composable(route = ItemScreens.ItemListScreen.route){
            MainItemScreen(mainNavController)
        }
        composable(route = RevenueScreens.RevenueListScreen.route){
            MainRevenueScreen(mainNavController)
        }
        composable(route = SavingScreens.SavingListScreen.route){
            MainSavingScreen(mainNavController)
        }
        composable(route = SupplierScreens.SupplierListScreen.route){
            MainSupplierScreen(mainNavController)
        }
        composable(route = CustomerScreens.AddCustomerScreen.route){
            AddCustomerScreen(navigateBackToCustomerListScreen = {mainNavController.popBackStack()})
        }
        composable(route = BankScreens.AddBankScreen.route){
            AddBankScreen(navigateBackToBankListScreen = {mainNavController.popBackStack()})
        }
        composable(route = ReportScreens.RevenueReportScreen.route){
            RevenueReportScreen(mainNavController)
        }
        composable(route = ReportScreens.SavingsReportScreen.route){
            SavingReportScreen(mainNavController)
        }
        composable(route = ReportScreens.ExpensesReportScreen.route){
            ExpensesReportScreen(mainNavController)
        }
        composable(route = ReportScreens.DebtorsReportScreen.route){
            DebtorsReportScreen(mainNavController)
        }
    }
}