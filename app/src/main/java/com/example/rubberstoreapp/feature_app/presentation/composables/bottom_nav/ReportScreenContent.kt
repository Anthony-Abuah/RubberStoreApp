package com.example.rubberstoreapp.feature_app.presentation.composables.bottom_nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.rubberstoreapp.feature_app.domain.repository.Banks
import com.example.rubberstoreapp.feature_app.domain.repository.Customers
import com.example.rubberstoreapp.feature_app.domain.repository.Items
import com.example.rubberstoreapp.feature_app.domain.repository.Suppliers
import com.example.rubberstoreapp.feature_app.presentation.composables.components.*
import com.example.rubberstoreapp.feature_app.presentation.composables.report.screens.ReportScreens
import com.example.rubberstoreapp.feature_app.presentation.theme.*
import java.time.LocalDate
import java.util.*

@Composable
@ExperimentalMaterialApi
fun ReportScreenContent(
    mainNavController: NavHostController,
    customers: Customers,
    suppliers: Suppliers,
    items: Items,
    banks: Banks,
    debt: Double,
    debtPayment: Double,
    revenue: Double,
    expenses: Double,
    savings: Double,
    monthlyDebt: Double,
    monthlyDebtRepayment: Double,
    monthlyRevenue: Double,
    monthlyExpenses: Double,
    monthlySavings: Double
) {
    var clicked by remember {
        mutableStateOf(true)
    }

    Box(
        modifier = Modifier
            .background(GeneralReportBackground)
            .fillMaxSize()
            .padding(top = 50.dp, bottom = 50.dp)
    ) {
        BasicScreenColumn {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(75.dp)
            ) {
                //Monthly and all time button indicator
                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = if (clicked) AllTimeButtonColor else ButtonBackgroundDisabled,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    elevation = 5.dp,
                    onClick = {
                        clicked = true
                    }) {
                    Box(
                        modifier = Modifier,
                        contentAlignment = Alignment.Center
                    ) {
                        ReportCardHeaderMenu(title = "All Time")
                    }
                }

                Spacer(modifier = Modifier.width(5.dp))

                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = if (clicked) ButtonBackgroundDisabled else AllTimeButtonColor,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    elevation = 5.dp,
                    onClick = {
                        clicked = false
                    }) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        val month =
                            LocalDate.now().month.toString().lowercase().capitalize(Locale.ROOT)
                        val year = LocalDate.now().year.toString()

                        ReportCardHeaderMenu(title = "$month, $year")
                    }
                }
            }


            Spacer(modifier = Modifier.height(50.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(125.dp)
            ) {
                //Rev and Expense Cards
                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = RevenueReportCardColor,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(1.dp),
                    elevation = 5.dp,
                    onClick = {
                        mainNavController.navigate(ReportScreens.RevenueReportScreen.route)
                    }
                ) {
                    Column(modifier = Modifier
                        .padding(
                        top = 16.dp,
                        bottom = 16.dp,
                        start = 8.dp,
                        end = 1.dp)
                    ) {
                        ReportCardTitle(title = "Revenue")
                        Spacer(modifier = Modifier.fillMaxHeight(0.6f))
                        ReportCardValue(value = if (clicked) "GHS ${revenue}0" else "GHS ${monthlyRevenue}0")
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))

                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = ExpenseReportCardColor,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(1.dp),
                    elevation = 5.dp,
                    onClick = {
                        mainNavController.navigate(ReportScreens.ExpensesReportScreen.route)
                    }) {
                    Column(modifier = Modifier
                        .padding(
                            top = 16.dp,
                            bottom = 16.dp,
                            start = 8.dp,
                            end = 1.dp)) {
                        ReportCardTitle(title = "Expenses")
                        Spacer(modifier = Modifier.fillMaxHeight(0.6f))
                        ReportCardValue(value = if (clicked) "GHS ${expenses}0" else "GHS ${monthlyExpenses}0")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            Row(
                //Profit, Savings
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(125.dp)
            ) {
                //Net Income Payment Card
                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = ProfitReportCardColor,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(1.dp),
                    elevation = 5.dp,
                    onClick = {}) {
                    Column(modifier = Modifier
                        .padding(
                            top = 16.dp,
                            bottom = 16.dp,
                            start = 8.dp,
                            end = 1.dp)) {
                        ReportCardTitle(title = "Net Income")
                        Spacer(modifier = Modifier.fillMaxHeight(0.6f))
                        ReportCardValue(value = if (clicked) "GHS ${revenue - expenses}0" else "GHS ${monthlyRevenue - monthlyExpenses}0")
                    }
                }


                Spacer(modifier = Modifier.width(10.dp))

                //Savings Card
                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = SavingsReportCardColor,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(1.dp),
                    elevation = 5.dp,
                    onClick = { mainNavController.navigate(ReportScreens.SavingsReportScreen.route) })
                {
                    Column(modifier = Modifier
                        .padding(
                            top = 16.dp,
                            bottom = 16.dp,
                            start = 8.dp,
                            end = 1.dp)) {
                        ReportCardTitle(title = "Savings")
                        Spacer(modifier = Modifier.fillMaxHeight(0.6f))
                        ReportCardValue(value = if (clicked) "GHS ${savings}0" else "GHS ${monthlySavings}0")
                    }
                }

            }

            Spacer(modifier = Modifier.height(16.dp))


            Row(
                //Debt, Debt Payment, Savings
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(125.dp)
            ) {
                //Debt Card
                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = DebtReportCardColor,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(1.dp),
                    elevation = 5.dp,
                    onClick = {
                        mainNavController.navigate(ReportScreens.DebtorsReportScreen.route)
                    }) {
                    Column(modifier = Modifier
                        .padding(
                            top = 16.dp,
                            bottom = 16.dp,
                            start = 8.dp,
                            end = 1.dp)) {
                        ReportCardTitle(title = "Debt")
                        Spacer(modifier = Modifier.fillMaxHeight(0.6f))
                        ReportCardValue(value = if (clicked) "GHS ${debt}0" else "GHS ${monthlyDebt}0")
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))

                //Debt Payment Card
                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = DebtPaymentReportCardColor,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(1.dp),
                    elevation = 5.dp,
                    onClick = {
                        mainNavController.navigate(ReportScreens.DebtorsReportScreen.route)
                    }) {
                    Column(modifier = Modifier
                        .padding(
                            top = 16.dp,
                            bottom = 16.dp,
                            start = 8.dp,
                            end = 1.dp)) {
                        ReportCardTitle(title = "Debt Payment")
                        Spacer(modifier = Modifier.fillMaxHeight(0.6f))
                        ReportCardValue(value = if (clicked) "GHS ${debtPayment}0" else "GHS ${monthlyDebtRepayment}0")
                    }
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            //CustomerList
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(230.dp),
                //.border(width = 1.8.dp, color = Color.Black, shape = RoundedCornerShape(15.dp)),
                backgroundColor = Color.White,
                elevation = 5.dp,
            ) {
                Column(
                    horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Top
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ReportCardHeader1(title = "Customers")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                        ) {
                            Box(
                                modifier = Modifier.weight(3f)
                            ) {
                                ReportCardHeader(title = "Customer's Name")
                            }
                            Box(
                                modifier = Modifier.weight(2f)
                            ) {
                                ReportCardHeader(title = "Contact")
                            }
                        }
                    }

                    Divider(thickness = 3.dp, color = ButtonBackgroundDisabled)

                    Box(
                        modifier = Modifier.heightIn(min = 60.dp, max = 150.dp)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 16.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top
                        ) {
                            items(items = customers) { customer ->
                                Column {
                                    Row(
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Box(
                                            modifier = Modifier.weight(3f)
                                        ) {
                                            Column {
                                                ReportCardValueItems(info = customer.customerName)
                                            }
                                        }
                                        Box(
                                            modifier = Modifier.weight(2f)
                                        ) {
                                            Column {
                                                ReportCardValueItems(info = customer.customerContact)
                                            }
                                        }

                                    }
                                    Divider(color = ButtonBackgroundDisabled)
                                }

                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            //ItemList
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(230.dp),
                //.border(width = 1.8.dp, color = Color.Black, shape = RoundedCornerShape(15.dp)),
                backgroundColor = Color.White,
                elevation = 5.dp,
            ) {
                Column(
                    horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Top
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ReportCardHeader1(title = "Items")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                        ) {
                            Box(
                                modifier = Modifier.weight(3f)
                            ) {
                                ReportCardHeader(title = "Item Name")
                            }
                            Box(
                                modifier = Modifier.weight(2f)
                            ) {
                                ReportCardHeader(title = "Manufacturer")
                            }
                        }
                    }

                    Divider(thickness = 3.dp, color = ButtonBackgroundDisabled)

                    Box(
                        modifier = Modifier.heightIn(min = 60.dp, max = 150.dp)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 16.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top
                        ) {
                            items(items = items) { item ->
                                Column() {
                                    Row(
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Box(
                                            modifier = Modifier.weight(3f)
                                        ) {
                                            Column {
                                                ReportCardValueItems(info = item.itemName)
                                            }
                                        }
                                        Box(
                                            modifier = Modifier.weight(2f)
                                        ) {
                                            Column {
                                                ReportCardValueItems(info = item.itemManufacturer)
                                            }
                                        }

                                    }
                                    Divider(color = ButtonBackgroundDisabled)
                                }
                            }
                        }
                    }
                }
            }


            Spacer(modifier = Modifier.height(16.dp))


            //Supplier's List
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(200.dp),
                //.border(width = 1.8.dp, color = Color.Black, shape = RoundedCornerShape(15.dp)),
                backgroundColor = Color.White,
                elevation = 5.dp,
            ) {
                Column(
                    horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Top
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ReportCardHeader1(title = "Suppliers")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                        ) {
                            Box(
                                modifier = Modifier.weight(3f)
                            ) {
                                ReportCardHeader(title = "Supplier's Name")
                            }
                            Box(
                                modifier = Modifier.weight(2f)
                            ) {
                                ReportCardHeader(title = "Contact")
                            }
                        }
                    }

                    Divider(thickness = 3.dp, color = ButtonBackgroundDisabled)

                    Box(
                        modifier = Modifier.heightIn(min = 60.dp, max = 150.dp)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 16.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top
                        ) {
                            items(items = suppliers) { supplier ->
                                Column() {
                                    Row(
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Box(
                                            modifier = Modifier.weight(3f)
                                        ) {
                                            Column {
                                                ReportCardValueItems(info = supplier.supplierName)
                                            }
                                        }
                                        Box(
                                            modifier = Modifier.weight(2f)
                                        ) {
                                            Column {
                                                ReportCardValueItems(info = supplier.supplierContact)
                                            }
                                        }

                                    }
                                    Divider(color = ButtonBackgroundDisabled)
                                }

                            }
                        }
                    }
                }
            }


            Spacer(modifier = Modifier.height(16.dp))


            //BankList
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(150.dp),
                //.border(width = 1.8.dp, color = Color.Black, shape = RoundedCornerShape(15.dp)),
                backgroundColor = Color.White,
                elevation = 5.dp,
            ) {

                Column(
                    horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Top
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ReportCardHeader1(title = "Banks")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                        ) {
                            Box(
                                modifier = Modifier.weight(3f)
                            ) {
                                ReportCardHeader(title = "Bank's Name")
                            }
                            Box(
                                modifier = Modifier.weight(2f)
                            ) {
                                ReportCardHeader(title = "Contact")
                            }
                        }
                    }

                    Divider(thickness = 3.dp, color = ButtonBackgroundDisabled)

                    Box(
                        modifier = Modifier.heightIn(min = 60.dp, max = 150.dp)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 16.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top
                        ) {
                            items(items = banks) { bank ->
                                Column() {
                                    Row(
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Box(
                                            modifier = Modifier.weight(3f)
                                        ) {
                                                ReportCardValueItems(info = bank.bankName)
                                        }
                                        Box(
                                            modifier = Modifier.weight(2f)
                                        ) {
                                                ReportCardValueItems(info = bank.bankContact)
                                        }
                                    }
                                    Divider(color = ButtonBackgroundDisabled)
                                }

                            }
                        }
                    }
                }
            }

        }
    }

}



