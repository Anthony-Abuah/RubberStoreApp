package com.example.rubberstoreapp.feature_app.presentation.composables.revenue

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.rubberstoreapp.feature_app.data.local.revenue.Revenue
import com.example.rubberstoreapp.feature_app.domain.repository.Revenues
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicLazyListScreenColumn
import com.example.rubberstoreapp.feature_app.presentation.composables.components.DeleteAlertDialog
import java.util.*

@Composable
@ExperimentalMaterialApi
fun RevenueContent(
    padding: PaddingValues,
    revenues: Revenues,
    dialog: Boolean,
    openDialog: () -> Unit,
    closeDialog: () -> Unit,
    deleteRevenue: (revenue: Revenue) -> Unit,
    navigateToUpdateRevenueScreen: (revenueId: Int) -> Unit
) {
    var revenue by remember {
        mutableStateOf(Revenue(0, Date(), 0.0))
    }

    BasicLazyListScreenColumn {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(
                items = revenues
            ) { theCurrentRevenue ->
                RevenueCard(
                    revenue = theCurrentRevenue,
                    removeRevenue = { currentRevenue ->
                        openDialog()
                        revenue = currentRevenue
                    },
                    navigateToUpdateRevenueScreen = navigateToUpdateRevenueScreen
                )
            }
        }
        DeleteAlertDialog(
            dialogTitle = "Delete Revenue",
            dialogMessage = "Are you sure you want to permanently delete revenue?",
            openDialog = dialog,
            delete = { deleteRevenue(revenue) },
            closeDialog = closeDialog
        )
    }

}