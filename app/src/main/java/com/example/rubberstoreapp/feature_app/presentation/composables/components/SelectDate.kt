package com.example.rubberstoreapp.feature_app.presentation.composables.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rubberstoreapp.core.util.Constants
import com.example.rubberstoreapp.feature_app.presentation.theme.MainTextFieldTextColor
import com.example.rubberstoreapp.feature_app.presentation.theme.TextFieldBackground
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun SelectDate(
    selectedDate: (date: LocalDate) -> Unit,
    initialDate: LocalDate,
    displayedDate: String
) {

    val dateDialogState = rememberMaterialDialogState()

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(TextFieldBackground, RoundedCornerShape(15.dp))
            .border(
                width = 1.8.dp,
                color = Color.Black,
                shape = RoundedCornerShape(15.dp)
            )
            .clickable {
                dateDialogState.show()
            },
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp),
            text = "Date: $displayedDate",
            color = MainTextFieldTextColor,
            textAlign = TextAlign.Start,
            style = TextStyle(
                color = MainTextFieldTextColor,
                fontSize = 16.sp)
        )
    }

    MaterialDialog(
        dialogState = dateDialogState,
        elevation = 10.dp,
        buttons = {
            positiveButton(text = Constants.OK) {
                Toast.makeText(context, "Date Selected", Toast.LENGTH_LONG).show()
            }
            negativeButton(text = Constants.CANCEL) {
                Toast.makeText(context, "The date was not changed", Toast.LENGTH_LONG).show()
            }
        }
    ) {
        datepicker(
            initialDate = initialDate,
            title = Constants.SELECT_DATE,
        ) { selectedDate ->
            selectedDate(selectedDate)
        }
    }
}