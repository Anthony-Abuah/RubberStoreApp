package com.example.rubberstoreapp.feature_app.presentation.composables.item

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.rubberstoreapp.R
import com.example.rubberstoreapp.core.util.Constants.ADD_ITEM
import com.example.rubberstoreapp.core.util.Constants.ITEM_DESCRIPTION
import com.example.rubberstoreapp.core.util.Constants.ITEM_MANUFACTURER
import com.example.rubberstoreapp.core.util.Constants.ITEM_NAME
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.item.Item
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicButton
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicImage
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicScreenColumn
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicTextField

@Composable
fun AddItemContent(
    paddingValues: PaddingValues,
    insertItem: (item: Item) -> Unit,
    navigateBack: () -> Unit
) {
    var itemName by remember { mutableStateOf(NO_VALUE) }
    var itemManufacturer by remember { mutableStateOf(NO_VALUE) }
    var itemDescription by remember { mutableStateOf(NO_VALUE) }

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
            value = itemName,
            onValueChange = { name ->
                itemName = name
            },
            placeholder = ITEM_NAME,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = itemManufacturer,
            onValueChange = { name ->
                itemManufacturer = name
            },
            placeholder = ITEM_MANUFACTURER,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = itemDescription,
            onValueChange = { name ->
                itemDescription = name
            },
            placeholder = ITEM_DESCRIPTION,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(50.dp))

        BasicButton(
            onClick = {
                val item = Item(0, itemName, itemManufacturer, itemDescription)

                if (itemName.isNotBlank()) {
                    insertItem(item)
                    Toast.makeText(context, "$itemName has been added to the list of items", Toast.LENGTH_LONG).show()
                    navigateBack()
                }
                else Toast.makeText(context, "Please enter Item name", Toast.LENGTH_LONG).show()
            },
            buttonName = ADD_ITEM
        )
    }

}