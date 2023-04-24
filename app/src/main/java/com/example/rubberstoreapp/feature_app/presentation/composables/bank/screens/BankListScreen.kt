package com.example.rubberstoreapp.feature_app.presentation.composables.bank.screens


import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rubberstoreapp.core.util.Constants.ADD_BANK
import com.example.rubberstoreapp.feature_app.presentation.composables.bank.BankContent
import com.example.rubberstoreapp.feature_app.presentation.composables.bank.BankScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.components.AddFloatingActionButton
import com.example.rubberstoreapp.feature_app.presentation.composables.components.AppTopBar
import com.example.rubberstoreapp.feature_app.presentation.composables.components.DeleteAlertDialog
import com.example.rubberstoreapp.feature_app.presentation.view_model.BankViewModel


@Composable
@ExperimentalMaterialApi
fun BankListScreen(
    bankViewModel: BankViewModel = hiltViewModel(),
    navigateToUpdateBankScreen: (bankId: Int) -> Unit,
    navController: NavController
) {
    val banks by bankViewModel.allBanks.collectAsState(
        initial = emptyList()
    )

    Scaffold(
        topBar = {
            AppTopBar(label = "Banks")
        },
        content = { padding ->
            BankContent(
                padding = padding,
                banks = banks,
                dialog = bankViewModel.openDialog,
                openDialog = { bankViewModel.openDialog() },
                closeDialog = { bankViewModel.closeDialog() },
                deleteBank = { bank ->
                    bankViewModel.deleteBank(bank)
                },
                navigateToUpdateBankScreen = navigateToUpdateBankScreen
            )
        },
        floatingActionButton = {
            AddFloatingActionButton(
                navigateToAnotherScreen =
                {
                    navController.navigate(BankScreens.AddBankScreen.route)
                },
                description = ADD_BANK)
        }
    )
}