package com.example.rubberstoreapp.feature_app.presentation.composables.supplier

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.rubberstoreapp.R
import com.example.rubberstoreapp.core.util.Constants.ADD_SUPPLIER
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.core.util.Constants.SUPPLIER_CONTACT
import com.example.rubberstoreapp.core.util.Constants.SUPPLIER_INFO
import com.example.rubberstoreapp.core.util.Constants.SUPPLIER_LOCATION
import com.example.rubberstoreapp.core.util.Constants.SUPPLIER_NAME
import com.example.rubberstoreapp.feature_app.data.local.supplier.Supplier
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicButton
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicImage
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicScreenColumn
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicTextField

@Composable
fun AddSupplierContent(
    padding: PaddingValues,
    insertSupplier: (supplier: Supplier) -> Unit,
    navigateBack: () -> Unit
) {
    var supplierName by remember { mutableStateOf(NO_VALUE) }
    var supplierContact by remember { mutableStateOf(NO_VALUE) }
    var supplierLocation by remember { mutableStateOf(NO_VALUE) }
    var supplierInfo by remember { mutableStateOf(NO_VALUE) }

    val context = LocalContext.current

    BasicScreenColumn {
        BasicImage(
            drawableResource = R.drawable.supplier,
            width = 200.dp,
            height = 200.dp,
            backgroundColor = Color.LightGray
        )

        Spacer(modifier = Modifier.height(50.dp))

        BasicTextField(
            value = supplierName,
            onValueChange = { name ->
                supplierName = name
            },
            placeholder = SUPPLIER_NAME,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = supplierContact,
            onValueChange = { contact ->
                supplierContact = contact
            },
            placeholder = SUPPLIER_CONTACT,
            keyboardType = KeyboardType.Phone
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = supplierLocation,
            onValueChange = { location ->
                supplierLocation = location
            },
            placeholder = SUPPLIER_LOCATION,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = supplierInfo,
            onValueChange = { info ->
                supplierInfo = info
            },
            placeholder = SUPPLIER_INFO,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(50.dp))

        BasicButton(
            onClick = {
                val supplier =
                    Supplier(0, supplierName, supplierContact, supplierLocation, supplierInfo)
                if (supplierName.isBlank()) {
                    Toast.makeText(context, "Please Enter the Supplier's Name", Toast.LENGTH_LONG)
                        .show()
                } else if (supplierContact.isBlank()) {
                    Toast.makeText(
                        context,
                        "Please enter ${supplierName}'s contact",
                        Toast.LENGTH_LONG
                    )
                        .show()
                } else {
                    insertSupplier(supplier)
                    Toast.makeText(context, "$supplierName has been added to your suppliers list", Toast.LENGTH_LONG).show()
                    navigateBack()
                }
            },
            buttonName = ADD_SUPPLIER
        )

    }
}