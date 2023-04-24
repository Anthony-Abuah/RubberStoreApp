package com.example.rubberstoreapp.feature_app.presentation.composables.bank.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.feature_app.presentation.composables.bank.AddBankContent
import com.example.rubberstoreapp.feature_app.presentation.composables.bank.AddBankTopBar
import com.example.rubberstoreapp.feature_app.presentation.view_model.BankViewModel

@Composable
fun AddBankScreen(
    bankViewModel: BankViewModel = hiltViewModel(),
    navigateBackToBankListScreen: () -> Unit
    ){

    Scaffold(
        topBar = {
            AddBankTopBar(
                navigateBack = navigateBackToBankListScreen
            )
        },
        content = { padding ->
            AddBankContent(
                paddingValues = padding,
                insertBank = { bank ->
                    bankViewModel.addBank(bank)
                },
                navigateBack = navigateBackToBankListScreen
            )
        }
    )
}