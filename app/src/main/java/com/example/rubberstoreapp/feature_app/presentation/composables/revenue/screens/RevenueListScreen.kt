package com.example.rubberstoreapp.feature_app.presentation.composables.revenue.screens


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
import com.example.rubberstoreapp.feature_app.presentation.composables.revenue.RevenueContent
import com.example.rubberstoreapp.feature_app.presentation.composables.revenue.RevenueScreens
import com.example.rubberstoreapp.feature_app.presentation.view_model.RevenueViewModel


@Composable
@ExperimentalMaterialApi
fun RevenueListScreen(
    revenueViewModel: RevenueViewModel = hiltViewModel(),
    navigateToUpdateRevenueScreen: (revenueId: Int) -> Unit,
    navController: NavController
) {
    val revenues by revenueViewModel.allRevenues.collectAsState(
        initial = emptyList()
    )

    Scaffold(
        topBar = {
            AppTopBar(label = "Revenue")
        },
        content = { padding ->
            RevenueContent(
                padding = padding,
                revenues = revenues,
                dialog = revenueViewModel.openDialog,
                openDialog = { revenueViewModel.openDialog() },
                closeDialog = { revenueViewModel.closeDialog() },
                deleteRevenue = { revenue ->
                    revenueViewModel.deleteRevenue(revenue)
                },
                navigateToUpdateRevenueScreen = navigateToUpdateRevenueScreen
            )
        },
        floatingActionButton = {
            AddFloatingActionButton(
                navigateToAnotherScreen =
                {
                    navController.navigate(RevenueScreens.AddRevenueScreen.route)
                },
                description = "Add Revenue")
        }
    )
}