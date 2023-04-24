package com.example.rubberstoreapp.feature_app.presentation.composables.savings

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.R
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.core.util.Constants.SAVINGS_AMOUNT
import com.example.rubberstoreapp.core.util.Constants.SELECT_BANK
import com.example.rubberstoreapp.core.util.Constants.SUSU_COLLECTOR
import com.example.rubberstoreapp.core.util.Constants.UPDATE_SAVINGS
import com.example.rubberstoreapp.feature_app.data.local.savings.Saving
import com.example.rubberstoreapp.feature_app.presentation.composables.components.*
import com.example.rubberstoreapp.feature_app.presentation.view_model.BankViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@Composable
fun UpdateSavingContent(
    bankViewModel: BankViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    savings: Saving,
    selectedItem: String,
    updateSavingsBankName: (bankName: String) -> Unit,
    updateSaving: (saving: Saving) -> Unit,
    updateSavingsDate: (date: Date) -> Unit,
    updateSavingsAmount: (amount: Double) -> Unit,
    updateSusuCollector: (name: String) -> Unit,
    navigateBack: () -> Unit,
    navigateToAddBankScreen: () -> Unit,
) {
    val shortFormat = "yyyy-MM-dd"
    val shortDateFormat = SimpleDateFormat(shortFormat, Locale.UK)

    val longFormat = "EEE, dd MMM, yyyy"
    val longDateFormat = SimpleDateFormat(longFormat, Locale.UK)

    val banks by bankViewModel.allBanks.collectAsState(
        initial = emptyList()
    )
    val bankNames = mutableListOf<String>()

    for (bank in banks) {
        bankNames.add(bank.bankName)
    }

    var bankName by remember {
        mutableStateOf(NO_VALUE)
    }

    val context = LocalContext.current

    BasicScreenColumn {
        BasicImage(
            drawableResource = R.drawable.savings,
            width = 200.dp,
            height = 200.dp,
            backgroundColor = Color.Transparent
        )

        Spacer(modifier = Modifier.height(50.dp))

        SelectDate(
            selectedDate = { date ->
                val updatedDate = java.sql.Date.valueOf(date.toString())
                updateSavingsDate(updatedDate)
            },
            initialDate = LocalDate.parse(shortDateFormat.format(savings.savingDate)),
            displayedDate = longDateFormat.format(savings.savingDate)
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = savings.savingAmount.toString(),
            onValueChange = { amount -> updateSavingsAmount(amount.toDouble()) },
            placeholder = SAVINGS_AMOUNT,
            keyboardType = KeyboardType.Decimal
        )

        Spacer(modifier = Modifier.height(25.dp))

        UpdateAutoCompleteWithAddButton(
            label = SELECT_BANK,
            listItems = bankNames,
            onClick = navigateToAddBankScreen,
            selectedItem = selectedItem,
            getInfo = { _bankName ->
                bankName = _bankName
                updateSavingsBankName(_bankName)
                if (bankNames.contains(_bankName)) {
                    bankViewModel.getBank(_bankName)
                }
            }
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = savings.susuCollector,
            onValueChange = { susuCollector -> updateSusuCollector(susuCollector) },
            placeholder = SUSU_COLLECTOR,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(50.dp))

        BasicButton(
            onClick = {
                val date = savings.savingDate
                val amount = savings.savingAmount
                val bank = bankViewModel.bank
                val susuCollector = savings.susuCollector
                val updatedSavings = Saving(savings.savingId, date, bank, amount, susuCollector)

                if (amount <= 0.0) {
                    Toast.makeText(context, "Please Enter Savings Amount", Toast.LENGTH_LONG).show()
                } else if (bankNames.isEmpty()) {
                    Toast.makeText(context, "Please select bank", Toast.LENGTH_LONG).show()
                } else if (!(bankNames.contains(bankName))) {
                    Toast.makeText(context, "$bankName is not in your bank list", Toast.LENGTH_LONG)
                        .show()
                    Toast.makeText(context, "Please add $bankName to your bank list", Toast.LENGTH_LONG)
                        .show()
                } else if (susuCollector.isBlank()) {
                    Toast.makeText(
                        context,
                        "No name Provided for Susu Collector",
                        Toast.LENGTH_LONG
                    ).show()
                    updateSaving(updatedSavings)
                    Toast.makeText(context, "Savings Updated", Toast.LENGTH_LONG).show()
                    navigateBack()
                } else {
                    updateSaving(updatedSavings)
                    Toast.makeText(context, "Savings Updated", Toast.LENGTH_LONG).show()
                    navigateBack()
                }
            },
            buttonName = UPDATE_SAVINGS
        )
    }
}