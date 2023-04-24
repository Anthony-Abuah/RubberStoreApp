package com.example.rubberstoreapp.feature_app.presentation.composables.savings.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.feature_app.presentation.composables.savings.AddSavingContent
import com.example.rubberstoreapp.feature_app.presentation.composables.savings.AddSavingTopBar
import com.example.rubberstoreapp.feature_app.presentation.view_model.SavingViewModel

@Composable
fun AddSavingScreen(
    savingViewModel: SavingViewModel = hiltViewModel(),
    navigateBackToSavingListScreen: () -> Unit,
    navigateToAddBankScreen: () -> Unit
    ){

    Scaffold(
        topBar = {
            AddSavingTopBar(
                navigateBack = navigateBackToSavingListScreen
            )
        },
        content = { padding ->
            AddSavingContent(
                paddingValues = padding,
                insertSaving = { saving ->
                    savingViewModel.addSaving(saving)
                },
                navigateBack = navigateBackToSavingListScreen,
                navigateToAddBankScreen = navigateToAddBankScreen
            )

        }
    )
}