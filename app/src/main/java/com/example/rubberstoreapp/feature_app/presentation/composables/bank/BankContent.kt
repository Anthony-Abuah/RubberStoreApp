package com.example.rubberstoreapp.feature_app.presentation.composables.bank

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.bank.Bank
import com.example.rubberstoreapp.feature_app.domain.repository.Banks
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicLazyListScreenColumn
import com.example.rubberstoreapp.feature_app.presentation.composables.components.DeleteAlertDialog

@Composable
@ExperimentalMaterialApi
fun BankContent(
    padding: PaddingValues,
    banks: Banks,
    dialog: Boolean,
    openDialog: () -> Unit,
    closeDialog: () -> Unit,
    deleteBank: (bank: Bank) -> Unit,
    navigateToUpdateBankScreen: (bankId: Int) -> Unit
) {
    var bank by remember {
        mutableStateOf(Bank(0, NO_VALUE, NO_VALUE, NO_VALUE))
    }

    BasicLazyListScreenColumn {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(
                items = banks
            ) { theCurrentBank ->
                BankCard(
                    bank = theCurrentBank,
                    currentBank = { currentBank ->
                        openDialog()
                        bank = currentBank
                    },
                    navigateToUpdateBankScreen = navigateToUpdateBankScreen
                )
            }
        }

        DeleteAlertDialog(
            dialogTitle = "Delete Bank",
            dialogMessage = "Are you sure you want to permanently delete bank?",
            openDialog = dialog,
            delete = { deleteBank(bank) },
            closeDialog = closeDialog
        )
    }
}