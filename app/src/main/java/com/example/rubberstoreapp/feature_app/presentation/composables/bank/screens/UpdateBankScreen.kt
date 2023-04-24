package com.example.rubberstoreapp.feature_app.presentation.composables.bank.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.feature_app.presentation.composables.bank.UpdateBankContent
import com.example.rubberstoreapp.feature_app.presentation.composables.bank.UpdateBankTopBar
import com.example.rubberstoreapp.feature_app.presentation.view_model.BankViewModel


@Composable
fun UpdateBankScreen (
    bankViewModel: BankViewModel = hiltViewModel(),
    bankId: Int,
    navigateBackToBankListScreen: () -> Unit
){

    LaunchedEffect(Unit) {
        bankViewModel.getBankById(bankId)
    }

    Scaffold(
        topBar = {
            UpdateBankTopBar(
                navigateBack = navigateBackToBankListScreen
            )
        },
        content = { padding ->
            UpdateBankContent(
                padding = padding,
                bank = bankViewModel.bank,
                updateBank = { bank->
                    bankViewModel.updateBank(bank)
                },
                updateBankName = {name->
                    bankViewModel.updateBankName(name) },
                updateBankContact = {contact->
                    bankViewModel.updateBankContact(contact) },
                updateBankLocation = {location->
                    bankViewModel.updateBankLocation(location) },
                navigateBack = navigateBackToBankListScreen
            )
        }
    )
}