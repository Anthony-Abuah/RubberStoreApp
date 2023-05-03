package com.example.rubberstoreapp.feature_app.presentation.composables.report.revenue

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
fun RevenueReportScreenContent(
    allTimeRevenues: Revenues,
    durationalRevenues: Revenues,
    allTimeMinRevenue: Double,
    allTimeMaximumRevenue: Double,
    allTimeAverageRevenue: Double,
    allTimeRevenueDays: Int,
    durationalMinimumRevenue: Double,
    durationalMaximumRevenue: Double,
    durationalAverageRevenue: Double,
    durationalRevenueDays: Int
) {
    var clicked by remember {
        mutableStateOf(true)
    }

    val longFormat = "EEE, dd MMM, yyyy"
    val simpleDateFormat = SimpleDateFormat(longFormat, Locale.UK)


    val allTimeRevenueDates = mutableMapOf<Double, String>()

    for (revenues in allTimeRevenues){
        allTimeRevenueDates[revenues.amount] = simpleDateFormat.format(revenues.date)
    }

    val durationalRevenueDates = mutableMapOf<Double, String>()

    for (revenues in durationalRevenues){
        durationalRevenueDates[revenues.amount] = simpleDateFormat.format(revenues.date)
    }


    Box(
        modifier = Modifier
            .background(ReportBackground)
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
                //Monthly Button Indicator
                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = if (clicked) SwitchButtonEnabled  else SwitchButtonDisabled,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    elevation = 5.dp,
                    onClick = { clicked = true }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        val month = LocalDate.now().month.toString().lowercase().capitalize(Locale.ROOT)
                        val year = LocalDate.now().year.toString()
                        ReportCardHeaderMenu(title = "$month, $year")
                    }
                }

                Spacer(modifier = Modifier.width(5.dp))

                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = if (clicked) SwitchButtonDisabled  else SwitchButtonEnabled,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    elevation = 5.dp,
                    onClick = {
                        clicked = false
                    }) {
                    Box(
                        modifier = Modifier,
                        contentAlignment = Alignment.Center
                    ) {
                        ReportCardHeaderMenu(title = "All Time")
                    }
                }
            }


            Spacer(modifier = Modifier.height(50.dp))

            //Min Revenue and Max Revenue Cards
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(125.dp)
            ) {
                //Min Revenue Card
                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = MinimumRevenueCardColor,
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
                            ReportCardTitle(title = "Minimum Revenue")
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .weight(2f)
                                .fillMaxWidth(), contentAlignment = Alignment.CenterStart
                        ) {
                            ReportCardTitle(title = if (clicked) "${durationalRevenueDates[durationalMinimumRevenue]}" else "${allTimeRevenueDates[allTimeMinRevenue]}")
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .weight(3f)
                                .fillMaxWidth(), contentAlignment = Alignment.BottomStart
                        ) {
                            ReportCardValue(value = if (clicked) "GHS ${durationalMinimumRevenue}0" else "GHS ${allTimeMinRevenue}0")
                        }
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))

                //Maximum Revenue Card
                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = MaximumRevenueCardColor,
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
                            ReportCardTitle(title = "Maximum Revenue")
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .weight(2f)
                                .fillMaxWidth(), contentAlignment = Alignment.CenterStart
                        ) {
                            ReportCardTitle(title = if (clicked) "${durationalRevenueDates[durationalMaximumRevenue]}" else "${allTimeRevenueDates[allTimeMaximumRevenue]}")
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .weight(3f)
                                .fillMaxWidth(), contentAlignment = Alignment.BottomStart
                        ) {
                            ReportCardValue(value = if (clicked) "GHS ${durationalMaximumRevenue}0" else "GHS ${allTimeMaximumRevenue}0")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            //Average Revenue, Revenue Days
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(125.dp)
            ) {
                //Average Revenue
                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = AverageRevenueCardColor,
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
                            ReportCardTitle(title = "Average Revenue")
                        }
                        Spacer(modifier = Modifier.weight(2f))
                        Box(
                            modifier = Modifier
                                .weight(3f)
                                .fillMaxWidth(), contentAlignment = Alignment.BottomStart
                        ) {
                            ReportCardValue(value = if (clicked) "GHS ${durationalAverageRevenue.toInt()}.00" else "GHS ${allTimeAverageRevenue.toInt()}.00")
                        }
                    }
                }


                Spacer(modifier = Modifier.width(10.dp))

                //Revenue Days Card
                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = RevenueDaysCardColor,
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
                            ReportCardTitle(title = "Revenue Days")
                        }
                        Spacer(modifier = Modifier.weight(2f))
                        Box(
                            modifier = Modifier
                                .weight(3f)
                                .fillMaxWidth(), contentAlignment = Alignment.BottomStart
                        ) {
                            ReportCardValue(value = if (clicked) "$durationalRevenueDays days" else "$allTimeRevenueDays days")
                        }
                    }
                }

            }

            Spacer(modifier = Modifier.height(25.dp))

            //Revenue List
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(350.dp),
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
                        ReportCardHeader1(title = "Revenue")
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
                            items(items = if (clicked) durationalRevenues else allTimeRevenues) { revenue ->
                                Column {
                                    Row(
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Box(
                                            modifier = Modifier.weight(3f)
                                        ) {
                                            Column {
                                                ReportCardValueItems(
                                                    info = simpleDateFormat.format(
                                                        revenue.date
                                                    )
                                                )
                                            }
                                        }
                                        Box(
                                            modifier = Modifier.weight(2f)
                                        ) {
                                            Column {
                                                ReportCardValueItems(info = "GHS ${revenue.amount}0")
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



