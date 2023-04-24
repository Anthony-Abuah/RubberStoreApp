package com.example.rubberstoreapp.feature_app.presentation.composables.savings

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.R
import com.example.rubberstoreapp.core.util.Constants.ADD_SAVINGS
import com.example.rubberstoreapp.core.util.Constants.CANCEL
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.core.util.Constants.OK
import com.example.rubberstoreapp.core.util.Constants.SAVINGS_AMOUNT
import com.example.rubberstoreapp.core.util.Constants.SELECT_BANK
import com.example.rubberstoreapp.core.util.Constants.SELECT_CUSTOMER
import com.example.rubberstoreapp.core.util.Constants.SELECT_DATE
import com.example.rubberstoreapp.core.util.Constants.SUSU_COLLECTOR
import com.example.rubberstoreapp.feature_app.data.local.savings.Saving
import com.example.rubberstoreapp.feature_app.presentation.composables.components.*
import com.example.rubberstoreapp.feature_app.presentation.view_model.BankViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AddSavingContent(
    bankViewModel: BankViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    insertSaving: (saving: Saving) -> Unit,
    navigateBack: () -> Unit,
    navigateToAddBankScreen: () -> Unit,
) {
    val banks by bankViewModel.allBanks.collectAsState(
        initial = emptyList()
    )
    val bankNames = mutableListOf<String>()

    for (bank in banks) {
        bankNames.add(bank.bankName)
    }

    var savingDate by remember { mutableStateOf(LocalDate.now()) }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("EEE, dd MMM, yyyy")
                .format(savingDate)
        }
    }
    val dateDialogState = rememberMaterialDialogState()

    var bankName by remember { mutableStateOf(NO_VALUE) }
    var savingAmount by remember { mutableStateOf(NO_VALUE) }
    var susuCollector by remember { mutableStateOf(NO_VALUE) }

    val context = LocalContext.current

    BasicScreenColumn {
        BasicImage(
            drawableResource = R.drawable.savings,
            width = 200.dp,
            height = 200.dp,
            backgroundColor = Color.LightGray
        )

        Spacer(modifier = Modifier.height(50.dp))

        SelectDate(
            selectedDate = { date -> savingDate = date },
            initialDate = LocalDate.now(),
            displayedDate = formattedDate
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = savingAmount,
            onValueChange = { amount -> savingAmount = amount },
            placeholder = SAVINGS_AMOUNT,
            keyboardType = KeyboardType.Decimal
        )

        Spacer(modifier = Modifier.height(25.dp))

        AutoCompleteWithAddButton(
            label = SELECT_BANK,
            listItems = bankNames,
            onClick = navigateToAddBankScreen,
            getInfo = { name ->
                bankName = name
                if (bankNames.contains(name)) {
                    bankViewModel.getBank(name)
                }

            }
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = susuCollector,
            onValueChange = { value -> susuCollector = value },
            placeholder = SUSU_COLLECTOR,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(50.dp))

        BasicButton(
            onClick = {
                val date = java.sql.Date.valueOf(savingDate.toString())
                val amount = if (savingAmount.isBlank()) {
                    0.0
                } else savingAmount.toDouble()

                val bank = bankViewModel.bank
                val saving = Saving(0, date, bank, amount, susuCollector)

                if (amount <= 0.0) {
                    Toast.makeText(context, "Please enter amount", Toast.LENGTH_LONG).show()
                } else if (!(bankNames.contains(bankName))) {
                    Toast.makeText(
                        context,
                        "$bankName is not found in your bank list",
                        Toast.LENGTH_LONG
                    ).show()
                    Toast.makeText(
                        context,
                        "Please add $bankName to your bank list",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    insertSaving(saving)
                    Toast.makeText(context, "Savings inserted", Toast.LENGTH_LONG).show()
                    navigateBack()
                }
            },
            buttonName = ADD_SAVINGS
        )
    }
}