package com.example.rubberstoreapp.feature_app.presentation.composables.debt_repayment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.rubberstoreapp.R
import com.example.rubberstoreapp.feature_app.data.local.debt_repayment.DebtRepayment
import com.example.rubberstoreapp.feature_app.presentation.composables.components.*
import java.text.SimpleDateFormat
import java.util.*

@Composable
@ExperimentalMaterialApi
fun DebtRepaymentCard(
    debtRepayment: DebtRepayment,
    currentDebtRepayment: (debtRepayment: DebtRepayment) -> Unit,
    navigateToUpdateDebtRepaymentScreen: (debtRepaymentId: Int) -> Unit
) {
    val myFormat = "EEE, dd MMM, yyyy"
    val simpleDateFormat = SimpleDateFormat(myFormat, Locale.UK)

    Card(
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier
            .requiredHeight(150.dp)
            .padding(1.dp)
            .fillMaxWidth(),
        elevation = 3.dp
    ) {
        Card(
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .fillMaxHeight()
                .padding(4.dp)
                .fillMaxWidth(),
            elevation = 10.dp,
            onClick = {
                navigateToUpdateDebtRepaymentScreen(debtRepayment.debtRepaymentId)
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(
                        top = 1.dp,
                        start = 8.dp,
                        end = 1.dp,
                        bottom = 1.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Box(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(R.drawable.debt_repayment),
                        contentDescription = ""
                    )
                }

                Spacer(modifier = Modifier.width(5.dp))

                Column(
                    modifier = Modifier
                        .weight(6f)
                        .padding(top = 8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                )
                {
                    CardTitle(
                        title = simpleDateFormat.format(debtRepayment.date)
                    )
                    CardDescription(
                        info = "Customer: ${debtRepayment.customer.customerName}"
                    )
                    CardContact(
                        contact = "Contact: ${debtRepayment.customer.customerContact}"
                    )
                    CardDescription(
                        info = "Paid GHS ${debtRepayment.amount}0"
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 100.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier,
                        contentAlignment = Alignment.Center
                    ) {
                        DeleteIcon(
                            delete = { currentDebtRepayment(debtRepayment) }
                        )
                    }
                }

            }
        }

    }
}