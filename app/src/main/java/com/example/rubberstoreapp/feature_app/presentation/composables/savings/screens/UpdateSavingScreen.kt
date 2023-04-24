package com.example.rubberstoreapp.feature_app.presentation.composables.savings.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.feature_app.presentation.composables.savings.UpdateSavingContent
import com.example.rubberstoreapp.feature_app.presentation.composables.savings.UpdateSavingTopBar
import com.example.rubberstoreapp.feature_app.presentation.view_model.SavingViewModel


@Composable
fun UpdateSavingScreen(
    savingViewModel: SavingViewModel = hiltViewModel(),
    savingId: Int,
    navigateBackToSavingListScreen: () -> Unit,
    navigateToAddBankScreen: () -> Unit
){

    LaunchedEffect(Unit) {
        savingViewModel.getSaving(savingId)
    }

    Scaffold(
        topBar = {
            UpdateSavingTopBar(
                navigateBack = navigateBackToSavingListScreen
            )
        },
        content = { padding ->
            UpdateSavingContent(
                paddingValues = padding,
                savings = savingViewModel.saving,
                updateSaving = {saving->
                    savingViewModel.updateSaving(saving)
                },
                updateSavingsBankName = { bankName->
                    savingViewModel.updateSavingsBankName(bankName)
                },
                updateSavingsDate = {date ->
                    savingViewModel.updateSavingDate(date)
                },
                updateSavingsAmount = {amount ->
                    savingViewModel.updateSavingAmount(amount)
                },
                updateSusuCollector = {susuCollector ->
                    savingViewModel.updateSavingSusuCollector(susuCollector)
                },
                selectedItem = savingViewModel.bank.bankName,
                navigateBack =  navigateBackToSavingListScreen ) {
                navigateToAddBankScreen()
            }
        }
    )
}