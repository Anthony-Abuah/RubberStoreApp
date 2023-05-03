package com.example.rubberstoreapp.feature_app.presentation.composables.bottom_nav

import android.content.ClipData.Item
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rubberstoreapp.R
import com.example.rubberstoreapp.feature_app.data.local.bank.Bank
import com.example.rubberstoreapp.feature_app.presentation.composables.bank.BankScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicScreenColumn
import com.example.rubberstoreapp.feature_app.presentation.composables.components.HomeCard
import com.example.rubberstoreapp.feature_app.presentation.composables.customer.CustomerScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.debt.DebtScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.debt_repayment.DebtRepaymentScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.expenses.ExpenseScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.inventory.InventoryScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.item.ItemScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.revenue.RevenueScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.savings.SavingScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.supplier.SupplierScreens
import com.example.rubberstoreapp.feature_app.presentation.theme.TextFieldBackground

@Composable
@ExperimentalMaterialApi
fun HomeScreenContent(
    paddingValues: PaddingValues,
    navController: NavHostController
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(TextFieldBackground)
            .padding(
                top = 50.dp,
                bottom = 50.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        BasicScreenColumn {

         Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(150.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ){
                    HomeCard(navigate = { navController.navigate(RevenueScreens.RevenueListScreen.route) }, label = "Revenue", drawableResource = R.drawable.revenue)
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ){
                    HomeCard(navigate = { navController.navigate(ExpenseScreens.ExpenseListScreen.route) }, label = "Expense", drawableResource = R.drawable.expenses)
                }

            }


         Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(150.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ){
                    HomeCard(navigate = { navController.navigate(DebtScreens.DebtListScreen.route) }, label = "Debt", drawableResource = R.drawable.debt)
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ){
                    HomeCard(navigate = { navController.navigate(DebtRepaymentScreens.DebtRepaymentListScreen.route) }, label = "Debt Payment", drawableResource = R.drawable.debt_repayment)
                }

            }


         Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(150.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ){
                    HomeCard(navigate = { navController.navigate(CustomerScreens.CustomerListScreen.route) }, label = "Customers", drawableResource = R.drawable.customer)
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ){
                    HomeCard(navigate = { navController.navigate(SupplierScreens.SupplierListScreen.route) }, label = "Supplier", drawableResource = R.drawable.supplier)
                }

            }


         Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(150.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ){
                    HomeCard(navigate = { navController.navigate(BankScreens.BankListScreen.route) }, label = "Bank", drawableResource = R.drawable.bank)
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ){
                    HomeCard(navigate = { navController.navigate(SavingScreens.SavingListScreen.route) }, label = "Savings", drawableResource = R.drawable.savings)
                }

            }


         Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(150.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ){
                    HomeCard(navigate = { navController.navigate(InventoryScreens.InventoryListScreen.route) }, label = "Inventory", drawableResource = R.drawable.inventory)
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ){
                    HomeCard(navigate = { navController.navigate(ItemScreens.ItemListScreen.route) }, label = "Item", drawableResource = R.drawable.item)
                }

            }


        }
    }
}