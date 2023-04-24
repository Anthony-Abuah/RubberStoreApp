package com.example.rubberstoreapp.feature_app.presentation.composables.report.savings

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
fun SavingsReportScreenContent(
    allTimeSavings: Savings,
    durationalSavings: Savings,
    allTimeMinimumSavings: Double,
    allTimeMaximumSavings: Double,
    allTimeAverageSavings: Double,
    allTimeNumberOfSavings: Int,
    durationalMinimumSavings: Double,
    durationalMaximumSavings: Double,
    durationalAverageSavings: Double,
    durationalNumberOfSavings: Int
) {
    var clicked by remember {
        mutableStateOf(true)
    }

    val longFormat = "EEE, dd MMM, yyyy"
    val simpleDateFormat = SimpleDateFormat(longFormat, Locale.UK)


    val allTimeSavingDates = mutableMapOf<Double, String>()

    for (savings in allTimeSavings){
        allTimeSavingDates[savings.savingAmount] = simpleDateFormat.format(savings.savingDate)
    }

    val durationalSavingDates = mutableMapOf<Double, String>()

    for (savings in durationalSavings){
        durationalSavingDates[savings.savingAmount] = simpleDateFormat.format(savings.savingDate)
    }


    Box(
        modifier = Modifier
            .background(SavingsReportBackground)
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
                    onClick = {
                        clicked = true
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

            //Minimum Savings and Maximum Savings Cards
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(125.dp)
            ) {
                //Minimum Savings Card
                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = MinimumSavingsCardColor,
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
                            ReportCardTitle(title = "Minimum Savings")
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .weight(2f)
                                .fillMaxWidth(), contentAlignment = Alignment.CenterStart
                        ) {
                            ReportCardTitle(title = if (clicked) "${durationalSavingDates[durationalMinimumSavings]}" else "${allTimeSavingDates[allTimeMinimumSavings]}")
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .weight(3f)
                                .fillMaxWidth(), contentAlignment = Alignment.BottomStart
                        ) {
                            ReportCardValue(value = if (clicked) "GHS ${durationalMinimumSavings}0" else "GHS ${allTimeMinimumSavings}0")
                        }
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))

                //Maximum Savings Card
                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = MaximumSavingsCardColor,
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
                            ReportCardTitle(title = "Maximum Saving")
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .weight(2f)
                                .fillMaxWidth(), contentAlignment = Alignment.CenterStart
                        ) {
                            ReportCardTitle(title = if (clicked) "${durationalSavingDates[durationalMaximumSavings]}" else "${allTimeSavingDates[allTimeMaximumSavings]}")
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .weight(3f)
                                .fillMaxWidth(), contentAlignment = Alignment.BottomStart
                        ) {
                            ReportCardValue(value = if (clicked) "GHS ${durationalMaximumSavings}0" else "GHS ${allTimeMaximumSavings}0")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            
            //Average Savings, Number Of Savings
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(125.dp)
            ) {
                //Average Saving
                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = AverageSavingsCardColor,
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
                            ReportCardTitle(title = "Average Savings")
                        }
                        Spacer(modifier = Modifier.weight(2f))
                        Box(
                            modifier = Modifier
                                .weight(3f)
                                .fillMaxWidth(), contentAlignment = Alignment.BottomStart
                        ) {
                            ReportCardValue(value = if (clicked) "GHS ${durationalAverageSavings.toInt()}.00" else "GHS ${allTimeAverageSavings.toInt()}.00")
                        }
                    }
                }


                Spacer(modifier = Modifier.width(10.dp))

                //Number Of Savings Card
                Card(shape = androidx.compose.material.MaterialTheme.shapes.small,
                    backgroundColor = NumberOfSavingsCardColor,
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
                            ReportCardTitle(title = "Number Of Savings")
                        }
                        Spacer(modifier = Modifier.weight(2f))
                        Box(
                            modifier = Modifier
                                .weight(3f)
                                .fillMaxWidth(), contentAlignment = Alignment.BottomStart
                        ) {
                            ReportCardValue(value = if (clicked) "$durationalNumberOfSavings days" else "$allTimeNumberOfSavings times")
                        }
                    }
                }

            }

            Spacer(modifier = Modifier.height(16.dp))
            
            Spacer(modifier = Modifier.height(25.dp))

            //Savings List
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
                        ReportCardHeader1(title = "Savings")
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
                            items(items = if (clicked) durationalSavings else allTimeSavings) { saving ->
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
                                                        saving.savingDate
                                                    )
                                                )
                                            }
                                        }
                                        Box(
                                            modifier = Modifier.weight(2f)
                                        ) {
                                            Column {
                                                ReportCardValueItems(info = "GHS ${saving.savingAmount}0")
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



