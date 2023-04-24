package com.example.rubberstoreapp.feature_app.presentation.composables.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.example.rubberstoreapp.feature_app.data.local.item.Item
import com.example.rubberstoreapp.feature_app.presentation.composables.components.*

@Composable
@ExperimentalMaterialApi
fun ItemCard(
    item: Item,
    currentItem: (item: Item) -> Unit,
    navigateToUpdateItemScreen: (itemId: Int) -> Unit
) {

    Card(
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier
            .requiredHeight(150.dp)
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
                navigateToUpdateItemScreen(item.itemId)
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(
                        top = 1.dp,
                        start = 8.dp,
                        end = 1.dp,
                        bottom = 1.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Box(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(R.drawable.item),
                        contentDescription = ""
                    )
                }

                Spacer(modifier = Modifier.width(5.dp))

                Column(
                    modifier = Modifier
                        .weight(6f)
                        .padding(top = 8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                )
                {
                    CardTitle(
                        title = item.itemName
                    )
                    CardDescription(
                        info = "Company: ${item.itemManufacturer}"
                    )
                    CardDescription(
                        info = item.itemDescription
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 100.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier,
                        contentAlignment = Alignment.Center
                    ) {
                        DeleteIcon(
                            delete = { currentItem(item) }
                        )
                    }
                }

            }
        }

    }
}