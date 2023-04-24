package com.example.rubberstoreapp.feature_app.presentation.composables.inventory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.rubberstoreapp.R
import com.example.rubberstoreapp.feature_app.data.local.inventory.Inventory
import com.example.rubberstoreapp.feature_app.presentation.composables.components.*
import java.text.SimpleDateFormat
import java.util.*

@Composable
@ExperimentalMaterialApi
fun InventoryCard(
    inventory: Inventory,
    currentInventory: (inventory: Inventory) -> Unit,
    navigateToUpdateInventoryScreen: (inventoryId: Int) -> Unit
) {
    val myFormat = "EEE, dd MMM, yyyy"
    val simpleDateFormat = SimpleDateFormat(myFormat, Locale.UK)

    Card(
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier
            .requiredHeight(350.dp)
            .padding(1.dp)
            .fillMaxWidth(),
        elevation = 3.dp
    ) {
        Card(
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .fillMaxHeight()
                .padding(4.dp)
                .fillMaxWidth(),
            elevation = 10.dp,
            onClick = {
                navigateToUpdateInventoryScreen(inventory.inventoryId)
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(
                        top = 1.dp,
                        start = 12.dp,
                        end = 12.dp,
                        bottom = 6.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                Column(
                    modifier = Modifier
                        .weight(9f)
                        .padding(top = 8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                )
                {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BasicImage(
                            drawableResource = R.drawable.inventory,
                            width = 40.dp,
                            height = 40.dp,
                            backgroundColor = Color.Transparent
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        CardTitle(
                            title = simpleDateFormat.format(inventory.date)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    InventoryItemsListCard(inventory = inventory)
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.weight(12f)) {
                            CardTitle(title = "Total: GHS ${inventory.totalCost}0")
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 1.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            DeleteIcon(delete = { currentInventory(inventory) })
                        }

                    }
                }
            }
        }
    }
}