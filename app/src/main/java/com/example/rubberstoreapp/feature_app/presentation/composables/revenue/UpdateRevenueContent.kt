package com.example.rubberstoreapp.feature_app.presentation.composables.revenue

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.rubberstoreapp.R
import com.example.rubberstoreapp.core.util.Constants.REVENUE_AMOUNT
import com.example.rubberstoreapp.core.util.Constants.UPDATE_REVENUE
import com.example.rubberstoreapp.feature_app.data.local.revenue.Revenue
import com.example.rubberstoreapp.feature_app.presentation.composables.components.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@Composable
fun UpdateRevenueContent(
    paddingValues: PaddingValues,
    revenue: Revenue,
    updateRevenueDate: (date: Date) -> Unit,
    updateAmount: (amount: Double) -> Unit,
    updateRevenue: (revenue: Revenue) -> Unit,
    navigateBack: () -> Unit
) {
    val shortFormat = "yyyy-MM-dd"
    val shortDateFormat = SimpleDateFormat(shortFormat, Locale.UK)

    val longFormat = "EEE, dd MMM, yyyy"
    val longDateFormat = SimpleDateFormat(longFormat, Locale.UK)

    val context = LocalContext.current

    BasicScreenColumn {
        BasicImage(
            drawableResource = R.drawable.revenue,
            width = 200.dp,
            height = 200.dp,
            backgroundColor = Color.LightGray
        )

        Spacer(modifier = Modifier.height(50.dp))

        SelectDate(
            selectedDate = { date ->
                val updatedDate = java.sql.Date.valueOf(date.toString())
                updateRevenueDate(updatedDate)
            },
            initialDate = LocalDate.parse(shortDateFormat.format(revenue.date)),
            displayedDate = longDateFormat.format(revenue.date)
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = revenue.amount.toString(),
            onValueChange = { amount ->
                updateAmount(amount.toDouble())
            },
            placeholder = REVENUE_AMOUNT,
            keyboardType = KeyboardType.Decimal
        )

        Spacer(modifier = Modifier.height(50.dp))

        BasicButton(
            onClick = {
                val date = revenue.date
                val amount = revenue.amount
                val updatedRevenue = Revenue(revenue.revenueId, date, amount)

                if (amount <= 0.0) {
                    Toast.makeText(context, "Please provide revenue amount", Toast.LENGTH_LONG)
                        .show()
                } else {
                    updateRevenue(updatedRevenue)
                    Toast.makeText(context, "Revenue Updated", Toast.LENGTH_LONG).show()
                    navigateBack()
                }
            },
            buttonName = UPDATE_REVENUE
        )

    }
}