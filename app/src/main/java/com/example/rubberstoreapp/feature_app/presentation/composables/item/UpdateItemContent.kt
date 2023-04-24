package com.example.rubberstoreapp.feature_app.presentation.composables.item

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.rubberstoreapp.R
import com.example.rubberstoreapp.core.util.Constants.ITEM_DESCRIPTION
import com.example.rubberstoreapp.core.util.Constants.ITEM_MANUFACTURER
import com.example.rubberstoreapp.core.util.Constants.ITEM_NAME
import com.example.rubberstoreapp.core.util.Constants.UPDATE_ITEM
import com.example.rubberstoreapp.feature_app.data.local.item.Item
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicButton
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicImage
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicScreenColumn
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicTextField


@Composable
fun UpdateItemContent(
    padding: PaddingValues,
    item: Item,
    updateItem: (item: Item) -> Unit,
    updateItemName: (name: String) -> Unit,
    updateItemManufacturer: (manufacturer: String) -> Unit,
    updateItemDescription: (description: String) -> Unit,
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    BasicScreenColumn {
        BasicImage(
            drawableResource = R.drawable.item,
            width = 200.dp,
            height = 200.dp,
            backgroundColor = Color.LightGray
        )

        Spacer(modifier = Modifier.height(50.dp))

        BasicTextField(
            value = item.itemName,
            onValueChange = { name ->
                updateItemName(name)
            },
            placeholder = ITEM_NAME,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = item.itemManufacturer,
            onValueChange = { manufacturer ->
                updateItemManufacturer(manufacturer)
            },
            placeholder = ITEM_MANUFACTURER,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = item.itemDescription,
            onValueChange = { description ->
                updateItemDescription(description)
            },
            placeholder = ITEM_DESCRIPTION,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(50.dp))

        BasicButton(
            onClick = {
                val itemId = item.itemId
                val itemName = item.itemName
                val itemManufacturer = item.itemManufacturer
                val itemDescription = item.itemDescription

                val updatedItem = Item(itemId, itemName, itemManufacturer, itemDescription)

                if (itemName.isBlank()) {
                    Toast.makeText(context, "Please Enter the Item's Name", Toast.LENGTH_LONG)
                        .show()
                }
                else {
                    updateItem(updatedItem)
                    Toast.makeText(context, "$itemName's details has been successfully updated", Toast.LENGTH_LONG).show()
                    navigateBack()
                }
            },
            buttonName = UPDATE_ITEM
        )

    }
}