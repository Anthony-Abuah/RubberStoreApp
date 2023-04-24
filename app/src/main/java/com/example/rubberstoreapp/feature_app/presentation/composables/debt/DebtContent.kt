package com.example.rubberstoreapp.feature_app.presentation.composables.debt

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.customer.Customer
import com.example.rubberstoreapp.feature_app.data.local.debt.Debt
import com.example.rubberstoreapp.feature_app.domain.repository.Debts
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicLazyListScreenColumn
import com.example.rubberstoreapp.feature_app.presentation.composables.components.DeleteAlertDialog
import java.util.*

@Composable
@ExperimentalMaterialApi
fun DebtContent(
    padding: PaddingValues,
    debts: Debts,
    dialog: Boolean,
    openDialog: () -> Unit,
    closeDialog: () -> Unit,
    deleteDebt: (debt: Debt) -> Unit,
    navigateToUpdateDebtScreen: (debtId: Int) -> Unit
) {
    val customer = Customer(0, NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE)
    var debt by remember {
        mutableStateOf(Debt(0, Date(), customer, 0.0))
    }

    BasicLazyListScreenColumn {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(
                items = debts
            ) { theCurrentDebt ->
                DebtCard(
                    debt = theCurrentDebt,
                    currentDebt = { currentDebt ->
                        openDialog()
                        debt = currentDebt
                    },
                    navigateToUpdateDebtScreen = navigateToUpdateDebtScreen
                )
            }
        }

        DeleteAlertDialog(
            dialogTitle = "Delete Debt",
            dialogMessage = "Are you sure you want to permanently delete debt?",
            openDialog = dialog,
            delete = {
                deleteDebt(debt)
            },
            closeDialog = closeDialog
        )
    }
}