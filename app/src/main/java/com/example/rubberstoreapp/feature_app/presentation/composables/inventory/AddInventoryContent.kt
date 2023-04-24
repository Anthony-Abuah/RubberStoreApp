package com.example.rubberstoreapp.feature_app.presentation.composables.inventory

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.R
import com.example.rubberstoreapp.core.util.Constants.ADD_INVENTORY
import com.example.rubberstoreapp.core.util.Constants.ADD_INVENTORY_ITEM
import com.example.rubberstoreapp.core.util.Constants.INVENTORY_COST
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.inventory.Inventory
import com.example.rubberstoreapp.feature_app.data.local.inventory_items.InventoryItems
import com.example.rubberstoreapp.feature_app.presentation.composables.components.*
import com.example.rubberstoreapp.feature_app.presentation.view_model.ItemViewModel
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AddInventoryContent(
    itemViewModel: ItemViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    inventoryItems: MutableList<InventoryItems>,
    insertInventory: (inventory: Inventory) -> Unit,
    navigateBack: () -> Unit
) {
    val items by itemViewModel.items.collectAsState(
        initial = emptyList()
    )

    var inventoryDate by remember { mutableStateOf(LocalDate.now()) }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("EEE, dd MMM, yyyy")
                .format(inventoryDate)
        }
    }
    val dateDialogState = rememberMaterialDialogState()

    var inventoryCost by remember { mutableStateOf(NO_VALUE) }

    var openAlertDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current

    BasicScreenColumn {
        BasicImage(
            drawableResource = R.drawable.inventory,
            width = 200.dp,
            height = 200.dp,
            backgroundColor = Color.LightGray
        )

        Spacer(modifier = Modifier.height(50.dp))

        SelectDate(
            selectedDate = { _date -> inventoryDate = _date },
            initialDate = LocalDate.now(),
            displayedDate = formattedDate
        )

        Spacer(modifier = Modifier.height(25.dp))

        InventoryItemsListPreviewCard(inventoryItems = inventoryItems)

        Spacer(modifier = Modifier.height(25.dp))

        BasicButton(onClick = { openAlertDialog = true }, buttonName = ADD_INVENTORY_ITEM)

        AddInventoryItemAlertDialog(
            openDialog = openAlertDialog,
            closeDialog = { openAlertDialog = false },
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = inventoryCost,
            onValueChange = { cost -> inventoryCost = cost },
            placeholder = INVENTORY_COST,
            keyboardType = KeyboardType.Decimal
        )

        Spacer(modifier = Modifier.height(50.dp))

        BasicButton(
            onClick = {
                val date = java.sql.Date.valueOf(inventoryDate.toString())
                val totalCost = if (inventoryCost.isBlank()) {
                    0.0
                } else inventoryCost.toDouble()
                val costs = mutableListOf<Double>()
                for (_cost in inventoryItems) {
                    costs.add(_cost.cost)
                }
                val totalCostOfInventory = costs.sum()

                if (inventoryItems.isEmpty()) {
                    Toast.makeText(context, "Add inventory items", Toast.LENGTH_LONG).show()
                }
                else if ((totalCost - totalCostOfInventory) > 2.0 || (totalCost < totalCostOfInventory)) {
                    Toast.makeText(
                        context,
                        "The total cost should be GHS ${totalCostOfInventory}0",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else {
                    val inventory = Inventory(0, date, inventoryItems, totalCost)
                    insertInventory(inventory)
                    Toast.makeText(context, "Inventory added successfully", Toast.LENGTH_LONG)
                        .show()
                    navigateBack()
                }
            },
            buttonName = ADD_INVENTORY
        )
    }
}