package com.example.rubberstoreapp.feature_app.presentation.composables.report.debtors

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
import com.example.rubberstoreapp.feature_app.domain.repository.*
import com.example.rubberstoreapp.feature_app.presentation.composables.components.*
import com.example.rubberstoreapp.feature_app.presentation.theme.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@Composable
@ExperimentalMaterialApi
fun DebtorsReportScreenContent(
    allTimeDebtAmount: Double,
    durationalDebtAmount: Double,
    allTimeDebtRepaymentAmount: Double,
    durationalDebtRepaymentAmount: Double,
    allTimeCustomerNameDebt: CustomerNameDebt,
    durationalCustomerNameDebt: CustomerNameDebt,
    allTimeCustomerNameDebtRepayment: CustomerNameDebtRepayment,
    durationalCustomerNameDebtRepayment: CustomerNameDebtRepayment,
    allTimeDebtDateValues: DebtDateValues,
    durationalDebtDateValues: DebtDateValues,
    allTimeDebtRepaymentDateValues: DebtRepaymentDateValue,
    durationalDebtRepaymentDateValues: DebtRepaymentDateValue
) {
    var clicked by remember {
        mutableStateOf(true)
    }

    val longFormat = "EEE, dd MMM, yyyy"
    val simpleDateFormat = SimpleDateFormat(longFormat, Locale.UK)

    Box(
        modifier = Modifier
            .background(DebtorsReportBackground)
            .fillMaxSize()
            .padding(top = 50.dp, bottom = 50.dp)
    ) {
        BasicScreenColumn {
            //Monthly and all time button indicator
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(75.dp)
            ) {
                //Monthly
                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = if (clicked) SwitchButtonEnabled else SwitchButtonDisabled,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    elevation = 5.dp,
                    onClick = { clicked = true }
                ){

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

                Spacer(modifier = Modifier.width(5.dp))

                //All Time
                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = if (clicked) SwitchButtonDisabled else SwitchButtonEnabled,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    elevation = 5.dp,
                    onClick = {
                        clicked = false
                    }
                ) {
                    Box(
                        modifier = Modifier,
                        contentAlignment = Alignment.Center
                    ) {
                        ReportCardHeaderMenu(title = "All Time")
                    }
                }

            }

            Spacer(modifier = Modifier.height(50.dp))

            //Debt And Debt Repayment Card
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(125.dp)
            ) {
                //Debts Card
                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = DebtsCardColor,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(1.dp),
                    elevation = 5.dp,
                    onClick = {}) {
                    Column(
                        modifier = Modifier
                            .padding(
                                top = 16.dp,
                                bottom = 16.dp,
                                start = 8.dp,
                                end = 1.dp
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(2f)
                                .fillMaxWidth(), contentAlignment = Alignment.TopStart
                        ) {
                            ReportCardTitle(title = "Total Debt Amount")
                        }
                        Spacer(modifier = Modifier.weight(2f))
                        Box(
                            modifier = Modifier
                                .weight(3f)
                                .fillMaxWidth(), contentAlignment = Alignment.BottomStart
                        ) {
                            ReportCardValue(value = if (clicked) "GHS ${durationalDebtAmount}0" else "GHS ${allTimeDebtAmount}0")
                        }
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))

                //Debt Repayment Card
                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = DebtRepaymentCardColor,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(1.dp),
                    elevation = 5.dp,
                    onClick = {}) {
                    Column(
                        modifier = Modifier
                            .padding(
                                top = 16.dp,
                                bottom = 16.dp,
                                start = 8.dp,
                                end = 1.dp
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(2f)
                                .fillMaxWidth(), contentAlignment = Alignment.TopStart
                        ) {
                            ReportCardTitle(title = "Total Debt Payments")
                        }
                        Spacer(modifier = Modifier.weight(2f))
                        Box(
                            modifier = Modifier
                                .weight(3f)
                                .fillMaxWidth(), contentAlignment = Alignment.BottomStart
                        ) {
                            ReportCardValue(value = if (clicked) "GHS ${durationalDebtRepaymentAmount}0" else "GHS ${allTimeDebtRepaymentAmount}0")
                        }
                    }
                }
            }



            Spacer(modifier = Modifier.height(16.dp))


            //Total Outstanding Debts Card
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(125.dp)
            ) {
                //Total Debts Card
                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = OutstandingDebtCardColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(1.dp),
                    elevation = 5.dp,
                    onClick = {}
                ) {
                    Column(
                        modifier = Modifier
                            .padding(
                                top = 16.dp,
                                bottom = 16.dp,
                                start = 8.dp,
                                end = 1.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(2f)
                                .fillMaxWidth(), contentAlignment = Alignment.TopStart
                        ) {
                            ReportCardTitle(title = "Outstanding Debt Amount")
                        }
                        Spacer(modifier = Modifier.weight(2f))
                        Box(
                            modifier = Modifier
                                .weight(3f)
                                .fillMaxWidth(), contentAlignment = Alignment.BottomStart
                        ) {
                            ReportCardValue(value = if (clicked) "GHS ${durationalDebtAmount - durationalDebtRepaymentAmount}0" else "GHS ${allTimeDebtAmount - allTimeDebtRepaymentAmount}0")
                        }

                    }

                }

            }



            Spacer(modifier = Modifier.height(50.dp))

            //Debt List
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(350.dp),
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
                        ReportCardHeader1(title = "Debts")
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
                                ReportCardHeader(title = "Date")
                            }
                            Box(
                                modifier = Modifier.weight(2f)
                            ) {
                                ReportCardHeader(title = "Amount")
                            }
                        }
                    }

                    Divider(thickness = 3.dp, color = SwitchButtonDisabled)

                    Box(
                        modifier = Modifier.heightIn(min = 150.dp, max = 300.dp)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 16.dp, top = 4.dp, end = 8.dp, bottom = 8.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top
                        ) {
                            items(items = if (clicked) durationalDebtDateValues else allTimeDebtDateValues) { debt ->
                                Column {
                                    Row(
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Box(
                                            modifier = Modifier.weight(3f)
                                        ) {
                                            Column {
                                                ReportCardValueItems(
                                                    info = simpleDateFormat.format(debt.date)
                                                )
                                            }
                                        }
                                        Box(
                                            modifier = Modifier.weight(2f)
                                        ) {
                                            Column {
                                                ReportCardValueItems(info = "GHS ${debt.amount}0")
                                            }
                                        }

                                    }
                                    Divider(color = SwitchButtonDisabled)
                                }

                            }
                        }
                    }
                }
            }


            Spacer(modifier = Modifier.height(50.dp))


            //Debt Payment List
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(350.dp),
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
                        ReportCardHeader1(title = "Debt Payment")
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
                                ReportCardHeader(title = "Date")
                            }
                            Box(
                                modifier = Modifier.weight(2f)
                            ) {
                                ReportCardHeader(title = "Amount")
                            }
                        }
                    }

                    Divider(thickness = 3.dp, color = SwitchButtonDisabled)

                    Box(
                        modifier = Modifier.heightIn(min = 150.dp, max = 300.dp)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 16.dp, top = 4.dp, end = 8.dp, bottom = 8.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top
                        ) {
                            items(items = if (clicked) durationalDebtRepaymentDateValues else allTimeDebtRepaymentDateValues) { debt ->
                                Column {
                                    Row(
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Box(
                                            modifier = Modifier.weight(3f)
                                        ) {
                                            Column {
                                                ReportCardValueItems(
                                                    info = simpleDateFormat.format(debt.date)
                                                )
                                            }
                                        }
                                        Box(
                                            modifier = Modifier.weight(2f)
                                        ) {
                                            Column {
                                                ReportCardValueItems(info = "GHS ${debt.amount}0")
                                            }
                                        }

                                    }
                                    Divider(color = SwitchButtonDisabled)
                                }

                            }
                        }
                    }
                }
            }


            Spacer(modifier = Modifier.height(50.dp))


            //Debt Customer List
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(350.dp),
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
                        ReportCardHeader1(title = "Debts")
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
                                ReportCardHeader(title = "Customer")
                            }
                            Box(
                                modifier = Modifier.weight(2f)
                            ) {
                                ReportCardHeader(title = "Debt")
                            }
                        }
                    }

                    Divider(thickness = 3.dp, color = SwitchButtonDisabled)

                    Box(
                        modifier = Modifier.heightIn(min = 150.dp, max = 300.dp)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 16.dp, top = 4.dp, end = 8.dp, bottom = 8.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top
                        ) {
                            items(items = if (clicked) durationalCustomerNameDebt else allTimeCustomerNameDebt) { debt ->
                                Column {
                                    Row(
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Box(
                                            modifier = Modifier.weight(3f)
                                        ) {
                                            Column {
                                                ReportCardValueItems(
                                                    info = debt.name
                                                )
                                            }
                                        }
                                        Box(
                                            modifier = Modifier.weight(2f)
                                        ) {
                                            Column {
                                                ReportCardValueItems(info = "GHS ${debt.amount}0")
                                            }
                                        }

                                    }
                                    Divider(color = SwitchButtonDisabled)
                                }

                            }
                        }
                    }
                }
            }



            Spacer(modifier = Modifier.height(50.dp))


            //Debt Payment Customer List
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(350.dp),
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
                        ReportCardHeader1(title = "Debt Payment")
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
                                ReportCardHeader(title = "Customer")
                            }
                            Box(
                                modifier = Modifier.weight(2f)
                            ) {
                                ReportCardHeader(title = "Debt Payment")
                            }
                        }
                    }

                    Divider(thickness = 3.dp, color = SwitchButtonDisabled)

                    Box(
                        modifier = Modifier.heightIn(min = 150.dp, max = 300.dp)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 16.dp, top = 4.dp, end = 8.dp, bottom = 8.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top
                        ) {
                            items(items = if (clicked) durationalCustomerNameDebtRepayment else allTimeCustomerNameDebtRepayment) { debt ->
                                Column {
                                    Row(
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Box(
                                            modifier = Modifier.weight(3f)
                                        ) {
                                            Column {
                                                ReportCardValueItems(
                                                    info = debt.name
                                                )
                                            }
                                        }
                                        Box(
                                            modifier = Modifier.weight(2f)
                                        ) {
                                            Column {
                                                ReportCardValueItems(info = "GHS ${debt.amount}0")
                                            }
                                        }

                                    }
                                    Divider(color = SwitchButtonDisabled)
                                }

                            }
                        }
                    }
                }
            }


        }
    }
}

