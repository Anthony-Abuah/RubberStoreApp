package com.example.rubberstoreapp.feature_app.presentation.composables.debt_repayment

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.rubberstoreapp.core.util.Constants
import com.example.rubberstoreapp.feature_app.data.local.customer.Customer
import com.example.rubberstoreapp.feature_app.data.local.debt_repayment.DebtRepayment
import com.example.rubberstoreapp.feature_app.domain.repository.DebtRepayments
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicLazyListScreenColumn
import com.example.rubberstoreapp.feature_app.presentation.composables.components.DeleteAlertDialog
import java.util.*

@Composable
@ExperimentalMaterialApi
fun DebtRepaymentContent(
    padding: PaddingValues,
    debtRepayments: DebtRepayments,
    dialog: Boolean,
    openDialog: () -> Unit,
    closeDialog: () -> Unit,
    deleteDebtRepayment: (debtRepayment: DebtRepayment) -> Unit,
    navigateToUpdateDebtRepaymentScreen: (debtRepaymentId: Int) -> Unit
) {
    val customer =
        Customer(0, Constants.NO_VALUE, Constants.NO_VALUE, Constants.NO_VALUE, Constants.NO_VALUE)
    var debtRepayment by remember {
        mutableStateOf(DebtRepayment(0, Date(), customer, 0.0))
    }

    BasicLazyListScreenColumn {


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(
                items = debtRepayments
            ) { theCurrentDebtRepayment ->
                DebtRepaymentCard(
                    debtRepayment = theCurrentDebtRepayment,
                    currentDebtRepayment = { currentDebtRepayment ->
                        openDialog()
                        debtRepayment = currentDebtRepayment
                    },
                    navigateToUpdateDebtRepaymentScreen = navigateToUpdateDebtRepaymentScreen
                )
            }
        }
        DeleteAlertDialog(
            dialogTitle = "Delete Debt Repayment",
            dialogMessage = "Are you sure you want to permanently delete debtRepayment?",
            openDialog = dialog,
            delete = { deleteDebtRepayment(debtRepayment) },
            closeDialog = closeDialog
        )
    }
}