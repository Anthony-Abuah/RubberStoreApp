package com.example.rubberstoreapp.feature_app.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.rubberstoreapp.feature_app.presentation.composables.bank.BankNavGraph
import com.example.rubberstoreapp.feature_app.presentation.composables.bottom_nav.BottomNavGraph
import com.example.rubberstoreapp.feature_app.presentation.composables.components.AppTopBar
import com.example.rubberstoreapp.feature_app.presentation.composables.customer.CustomerNavGraph
import com.example.rubberstoreapp.feature_app.presentation.composables.debt.DebtNavGraph
import com.example.rubberstoreapp.feature_app.presentation.composables.debt_repayment.DebtRepaymentNavGraph
import com.example.rubberstoreapp.feature_app.presentation.composables.expenses.ExpenseNavGraph
import com.example.rubberstoreapp.feature_app.presentation.composables.inventory.InventoryNavGraph
import com.example.rubberstoreapp.feature_app.presentation.composables.item.ItemNavGraph
import com.example.rubberstoreapp.feature_app.presentation.composables.revenue.RevenueNavGraph
import com.example.rubberstoreapp.feature_app.presentation.composables.savings.SavingNavGraph
import com.example.rubberstoreapp.feature_app.presentation.composables.supplier.SupplierNavGraph


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController
){

    Scaffold(
       /* bottomBar = {
        BottomBar(navHostController = navController)
    }*/
    ) {it
        BottomNavGraph(navController)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainBankScreen(
    navController: NavHostController
){

    Scaffold(topBar = {
        AppTopBar(label = "Bank")
    }) {it
        BankNavGraph()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCustomerScreen(
){

    Scaffold(
    ) {it
        CustomerNavGraph()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDebtRepaymentScreen(
    navController: NavHostController
){

    Scaffold() {it
        DebtRepaymentNavGraph(navController)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDebtScreen(
    mainNavController: NavHostController
){

    Scaffold() {it
        DebtNavGraph(mainNavController)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainExpenseScreen(
    navController: NavHostController
){

    Scaffold() {it
        ExpenseNavGraph()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainInventoryScreen(
    navController: NavHostController
){

    Scaffold() {it
        InventoryNavGraph()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainItemScreen(
    navController: NavHostController
){
    Scaffold() {it
        ItemNavGraph()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainRevenueScreen(
    navController: NavHostController
){
    Scaffold() {it
        RevenueNavGraph()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSavingScreen(
    navController: NavHostController
){
    Scaffold() {it
        SavingNavGraph(navController)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSupplierScreen(
    navController: NavHostController
){
    Scaffold() {it
        SupplierNavGraph()
    }

}
