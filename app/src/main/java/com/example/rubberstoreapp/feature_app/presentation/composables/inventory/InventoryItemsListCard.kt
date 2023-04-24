package com.example.rubberstoreapp.feature_app.presentation.composables.inventory

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rubberstoreapp.feature_app.data.local.inventory.Inventory
import com.example.rubberstoreapp.feature_app.presentation.composables.components.CardDescription
import com.example.rubberstoreapp.feature_app.presentation.theme.TextFieldBackground

@Composable
fun InventoryItemsListCard(
    inventory: Inventory,
) {
    val inventoryItems = inventory.items
    val itemNames = mutableListOf<String>()
    val itemCost = mutableListOf<Double>()
    val itemQuantity = mutableListOf<Double>()

    for (item in inventoryItems) {
        itemNames.add(item.itemName)
        itemCost.add(item.cost)
        itemQuantity.add(item.quantity)
    }

    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .border(width = 1.8.dp, color = Color.Black, shape = RoundedCornerShape(15.dp)),
        backgroundColor = TextFieldBackground,
        elevation = 3.dp,
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(3f)
                    ) {
                        CardDescription(info = "Item Name")
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        CardDescription(info = "Qty")
                    }
                    Box(
                        modifier = Modifier
                            .weight(2f)
                    ) {
                        CardDescription(info = "Cost(GHS)")
                    }
                }
            }
            Divider(color = Color.Black, thickness = 3.dp)
            Box(
                modifier = Modifier
                    .heightIn(min = 60.dp, max = 200.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    items(items = inventoryItems) { item ->
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(3f)
                                ) {
                                    CardDescription(info = item.itemName)
                                }
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                ) {
                                    CardDescription(info = "${item.quantity}")
                                }
                                Box(
                                    modifier = Modifier
                                        .weight(2f)
                                ) {
                                    CardDescription(info = "${item.cost}0")
                                }
                            }
                            Divider(color = Color.Black)
                        }
                    }
                }

            }
        }
    }
}