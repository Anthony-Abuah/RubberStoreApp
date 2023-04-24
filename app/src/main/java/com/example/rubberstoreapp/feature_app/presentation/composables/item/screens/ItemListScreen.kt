package com.example.rubberstoreapp.feature_app.presentation.composables.item.screens


import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rubberstoreapp.core.util.Constants.ADD_ITEM
import com.example.rubberstoreapp.feature_app.presentation.composables.item.ItemContent
import com.example.rubberstoreapp.feature_app.presentation.composables.item.ItemScreens
import com.example.rubberstoreapp.feature_app.presentation.composables.components.AddFloatingActionButton
import com.example.rubberstoreapp.feature_app.presentation.composables.components.AppTopBar
import com.example.rubberstoreapp.feature_app.presentation.composables.components.DeleteAlertDialog
import com.example.rubberstoreapp.feature_app.presentation.view_model.ItemViewModel


@Composable
@ExperimentalMaterialApi
fun ItemListScreen(
    itemViewModel: ItemViewModel = hiltViewModel(),
    navigateToUpdateItemScreen: (itemId: Int) -> Unit,
    navController: NavController
) {
    val items by itemViewModel.items.collectAsState(
        initial = emptyList()
    )

    Scaffold(
        topBar = {
            AppTopBar(label = "Items")
        },
        content = { padding ->
            ItemContent(
                padding = padding,
                items = items,
                dialog = itemViewModel.openDialog,
                openDialog = { itemViewModel.openDialog() },
                closeDialog = { itemViewModel.closeDialog() },
                deleteItem = { item ->
                    itemViewModel.deleteItem(item)
                },
                navigateToUpdateItemScreen = navigateToUpdateItemScreen
            )
        },
        floatingActionButton = {
            AddFloatingActionButton(
                navigateToAnotherScreen =
                {
                    navController.navigate(ItemScreens.AddItemScreen.route)
                },
                description = ADD_ITEM)
        }
    )
}