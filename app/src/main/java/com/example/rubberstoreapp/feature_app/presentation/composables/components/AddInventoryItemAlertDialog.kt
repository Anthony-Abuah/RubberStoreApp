package com.example.rubberstoreapp.feature_app.presentation.composables.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.core.util.Constants.ADD_INVENTORY_ITEM
import com.example.rubberstoreapp.core.util.Constants.CANCEL
import com.example.rubberstoreapp.core.util.Constants.ITEM_COST
import com.example.rubberstoreapp.core.util.Constants.ITEM_QUANTITY
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.inventory_items.InventoryItems
import com.example.rubberstoreapp.feature_app.presentation.view_model.InventoryViewModel
import com.example.rubberstoreapp.feature_app.presentation.view_model.ItemViewModel

@Composable
fun AddInventoryItemAlertDialog(
    itemViewModel: ItemViewModel = hiltViewModel(),
    inventoryViewModel: InventoryViewModel = hiltViewModel(),
    openDialog: Boolean,
    closeDialog: () -> Unit,
) {
        if (openDialog) {
            val context = LocalContext.current
            //var inventoryItemsList = mutableListOf<InventoryItems>()
            val items by itemViewModel.items.collectAsState(
                initial = emptyList()
            )
            val itemNames = mutableListOf<String>()

            for (item in items) {
                itemNames.add(item.itemName)
            }

            var itemName by remember { mutableStateOf(NO_VALUE) }
            var itemQuantity by remember { mutableStateOf(NO_VALUE) }
            var itemCost by remember { mutableStateOf(NO_VALUE) }
            val focusRequester = FocusRequester()

            AlertDialog(
                modifier = Modifier
                    .padding(8.dp),
                onDismissRequest = {
                    Toast.makeText(context, "Inventory item not added", Toast.LENGTH_LONG).show()
                    closeDialog()
                },

                text = {
                    Column {
                        Text(
                            color= Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            text = ADD_INVENTORY_ITEM
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            color= Color.Red,
                            fontWeight = FontWeight.Light,
                            fontSize = 14.sp,
                            text = "NB: Inventory items added cannot be edited."
                        )

                        Spacer(modifier = Modifier.height(16.dp))


                        AutoComplete(
                            label = "Select Item",
                            listItems = itemNames
                        ) { selectedItem ->
                            itemName = selectedItem
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        BasicTextField(
                            value = itemQuantity,
                            onValueChange = { quantity -> itemQuantity = quantity },
                            placeholder = ITEM_QUANTITY,
                            keyboardType = KeyboardType.Decimal
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        BasicTextField(
                            value = itemCost,
                            onValueChange = { quantity -> itemCost = quantity },
                            placeholder = ITEM_COST,
                            keyboardType = KeyboardType.Decimal
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            if (itemName.isBlank()){
                                Toast.makeText(context,"Please select Item", Toast.LENGTH_LONG).show()
                            }
                            else if (itemQuantity.isBlank()){
                                Toast.makeText(context,"Please enter the quantity of items", Toast.LENGTH_LONG).show()
                            }
                            else if (itemCost.isBlank()){
                                Toast.makeText(context,"Please enter the cost", Toast.LENGTH_LONG).show()
                            }
                            else{
                                inventoryViewModel.addToInventoryItem(itemName, itemQuantity.toDouble(), itemCost.toDouble())
                                val inventoryItem = inventoryViewModel.inventoryItem
                                inventoryViewModel.addInventoryItemToListOfInventoryItems(inventoryItem)
                                Toast.makeText(context,"Inventory added successfully", Toast.LENGTH_LONG).show()
                                closeDialog()
                            }
                        }
                    ) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = ADD_INVENTORY_ITEM
                        )
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            Toast.makeText(
                                context,
                                "Inventory item not added",
                                Toast.LENGTH_LONG
                            ).show()
                            closeDialog()
                        }
                    ) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = CANCEL
                        )
                    }
                }
            )
        }
}