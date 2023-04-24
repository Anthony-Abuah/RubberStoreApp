package com.example.rubberstoreapp.feature_app.presentation.composables.savings

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
import com.example.rubberstoreapp.feature_app.data.local.savings.Saving
import com.example.rubberstoreapp.feature_app.domain.repository.Savings
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicLazyListScreenColumn
import com.example.rubberstoreapp.feature_app.presentation.composables.components.DeleteAlertDialog
import java.util.*

@Composable
@ExperimentalMaterialApi
fun SavingContent(
    padding: PaddingValues,
    allSavings: Savings,
    dialog: Boolean,
    openDialog: () -> Unit,
    closeDialog: () -> Unit,
    deleteSavings: (savings: Saving) -> Unit,
    navigateToUpdateSavingScreen: (savingId: Int) -> Unit
) {
    var savings by remember {
        mutableStateOf(Saving(0, Date(), Bank(0, NO_VALUE, NO_VALUE, NO_VALUE), 0.0, NO_VALUE))
    }

    BasicLazyListScreenColumn {


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(
                items = allSavings
            ) { theCurrentSavings ->
                SavingCard(
                    saving = theCurrentSavings,
                    currentSaving = { currentSavings ->
                        openDialog()
                        savings = currentSavings
                    },
                    navigateToUpdateSavingScreen = navigateToUpdateSavingScreen
                )
            }
        }

        DeleteAlertDialog(
            dialogTitle = "Delete Savings",
            dialogMessage = "Are you sure you want to permanently delete savings?",
            openDialog = dialog,
            delete = { deleteSavings(savings) },
            closeDialog = closeDialog
        )
    }
}