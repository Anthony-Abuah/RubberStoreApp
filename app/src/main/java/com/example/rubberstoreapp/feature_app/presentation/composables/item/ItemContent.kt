package com.example.rubberstoreapp.feature_app.presentation.composables.item

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.item.Item
import com.example.rubberstoreapp.feature_app.domain.repository.Items
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicScreenColumn
import com.example.rubberstoreapp.feature_app.presentation.composables.components.DeleteAlertDialog

@Composable
@ExperimentalMaterialApi
fun ItemContent(
    padding: PaddingValues,
    items: Items,
    dialog: Boolean,
    openDialog: () -> Unit,
    closeDialog: () -> Unit,
    deleteItem: (item: Item) -> Unit,
    navigateToUpdateItemScreen: (itemId: Int) -> Unit
) {
    var item by remember {
        mutableStateOf(Item(0, NO_VALUE, NO_VALUE, NO_VALUE))
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)
    ) {
        items(
            items = items
        ) { theCurrentItem ->
            ItemCard(
                item = theCurrentItem,
                currentItem = { currentItem ->
                    openDialog()
                    item = currentItem
                },
                navigateToUpdateItemScreen = navigateToUpdateItemScreen
            )
        }
    }

    DeleteAlertDialog(
        dialogTitle = "Delete Item",
        dialogMessage = "Are you sure you want to permanently delete item?",
        openDialog = dialog,
        delete = { deleteItem(item) },
        closeDialog = closeDialog
    )

}