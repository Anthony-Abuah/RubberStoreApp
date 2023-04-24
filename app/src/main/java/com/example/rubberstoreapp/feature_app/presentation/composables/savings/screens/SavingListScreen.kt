package com.example.rubberstoreapp.feature_app.presentation.composables.savings.screens


import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rubberstoreapp.feature_app.presentation.composables.components.AddFloatingActionButton
import com.example.rubberstoreapp.feature_app.presentation.composables.components.AppTopBar
import com.example.rubberstoreapp.feature_app.presentation.composables.components.DeleteAlertDialog
import com.example.rubberstoreapp.feature_app.presentation.composables.savings.SavingContent
import com.example.rubberstoreapp.feature_app.presentation.composables.savings.SavingScreens
import com.example.rubberstoreapp.feature_app.presentation.view_model.SavingViewModel


@Composable
@ExperimentalMaterialApi
fun SavingListScreen(
    savingViewModel: SavingViewModel = hiltViewModel(),
    navigateToUpdateSavingScreen: (savingId: Int) -> Unit,
    navController: NavController
) {
    val savings by savingViewModel.allSavings.collectAsState(
        initial = emptyList()
    )

    Scaffold(
        topBar = {
            AppTopBar(label = "Savings")
        },
        content = { padding ->
            SavingContent(
                padding = padding,
                allSavings = savings,
                dialog = savingViewModel.openDialog,
                openDialog = { savingViewModel.openDialog() },
                closeDialog = { savingViewModel.closeDialog() },
                deleteSavings = { savings ->
                    savingViewModel.deleteSaving(savings)
                },
                navigateToUpdateSavingScreen = navigateToUpdateSavingScreen
            )

        },
        floatingActionButton = {
            AddFloatingActionButton(
                navigateToAnotherScreen =
                {
                    navController.navigate(SavingScreens.AddSavingScreen.route)
                },
                description = "Add Saving")
        }
    )
}