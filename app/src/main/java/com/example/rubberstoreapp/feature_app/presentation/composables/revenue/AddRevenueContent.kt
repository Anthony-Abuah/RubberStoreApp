package com.example.rubberstoreapp.feature_app.presentation.composables.revenue

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.rubberstoreapp.R
import com.example.rubberstoreapp.core.util.Constants.ADD_REVENUE
import com.example.rubberstoreapp.core.util.Constants.CANCEL
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.core.util.Constants.OK
import com.example.rubberstoreapp.core.util.Constants.REVENUE_AMOUNT
import com.example.rubberstoreapp.core.util.Constants.SELECT_DATE
import com.example.rubberstoreapp.feature_app.data.local.revenue.Revenue
import com.example.rubberstoreapp.feature_app.domain.repository.Revenues
import com.example.rubberstoreapp.feature_app.presentation.composables.components.*
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun AddRevenueContent(
    paddingValues: PaddingValues,
    revenues: Revenues,
    insertRevenue: (revenue: Revenue) -> Unit,
    navigateBack: () -> Unit
) {

    val longFormat = "EEE, dd MMM, yyyy"
    val longDateFormat = SimpleDateFormat(longFormat, Locale.UK)

    val revenueDates = mutableListOf<String>()

    for(revenue in revenues){
        revenueDates.add(longDateFormat.format(revenue.date))
    }

    var revenueDate by remember { mutableStateOf(LocalDate.now()) }
    val formattedDate by remember {
        derivedStateOf{
            DateTimeFormatter
                .ofPattern("EEE, dd MMM, yyyy")
                .format(revenueDate)
        }
    }

    var revenueAmount by remember { mutableStateOf(NO_VALUE) }

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
            selectedDate = {date -> revenueDate = date },
            initialDate = LocalDate.now(),
            displayedDate = formattedDate
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = revenueAmount,
            onValueChange = {amount->
                revenueAmount = amount
            },
            placeholder = REVENUE_AMOUNT,
            keyboardType = KeyboardType.Decimal
        )

        Spacer(modifier = Modifier.height(50.dp))

        BasicButton(
            onClick = {
                val date = java.sql.Date.valueOf(revenueDate.toString())
                val amount = if (revenueAmount.isBlank()){0.0} else revenueAmount.toDouble()
                val revenue = Revenue(0, date, amount)
                val longDate = longDateFormat.format(date)

                if (revenueDates.contains(longDate)){
                    Toast.makeText(context,"Revenue has already been provided for $longDate", Toast.LENGTH_LONG).show()
                    Toast.makeText(context,"You can go and update revenue for $longDate", Toast.LENGTH_LONG).show()
                }
                else if (amount <= 0.0){
                    Toast.makeText(context,"Please provide revenue amount", Toast.LENGTH_LONG).show()
                }
                else{
                    insertRevenue(revenue)
                    Toast.makeText(context,"Revenue Inserted", Toast.LENGTH_LONG).show()
                    navigateBack()
                }
            },
            buttonName = ADD_REVENUE
        )

    }
}