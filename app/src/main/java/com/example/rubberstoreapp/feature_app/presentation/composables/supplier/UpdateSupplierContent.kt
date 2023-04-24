package com.example.rubberstoreapp.feature_app.presentation.composables.supplier

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.rubberstoreapp.R
import com.example.rubberstoreapp.core.util.Constants.SUPPLIER_CONTACT
import com.example.rubberstoreapp.core.util.Constants.SUPPLIER_INFO
import com.example.rubberstoreapp.core.util.Constants.SUPPLIER_LOCATION
import com.example.rubberstoreapp.core.util.Constants.SUPPLIER_NAME
import com.example.rubberstoreapp.core.util.Constants.UPDATE_SUPPLIER
import com.example.rubberstoreapp.feature_app.data.local.supplier.Supplier
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicButton
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicImage
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicScreenColumn
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicTextField


@Composable
fun UpdateSupplierContent(
    padding: PaddingValues,
    supplier: Supplier,
    updateSupplier: (supplier: Supplier) -> Unit,
    updateSupplierName: (name: String) -> Unit,
    updateSupplierContact: (contact: String) -> Unit,
    updateSupplierLocation: (location: String) -> Unit,
    updateSupplierInfo: (info: String) -> Unit,
    navigateBack: () -> Unit
) {
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
            value = supplier.supplierName,
            onValueChange = { name ->
                updateSupplierName(name)
            },
            placeholder = SUPPLIER_NAME,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = supplier.supplierContact,
            onValueChange = { contact ->
                updateSupplierContact(contact)
            },
            placeholder = SUPPLIER_CONTACT,
            keyboardType = KeyboardType.Phone
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = supplier.supplierLocation,
            onValueChange = { location ->
                updateSupplierLocation(location)
            },
            placeholder = SUPPLIER_LOCATION,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = supplier.supplierInfo,
            onValueChange = { info ->
                updateSupplierInfo(info)
            },
            placeholder = SUPPLIER_INFO,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(50.dp))

        BasicButton(
            onClick = {
                val supplierId = supplier.supplierId
                val supplierName = supplier.supplierName
                val supplierContact = supplier.supplierContact
                val supplierLocation = supplier.supplierLocation
                val supplierInfo = supplier.supplierInfo

                val updatedSupplier = Supplier(
                    supplierId,
                    supplierName,
                    supplierContact,
                    supplierLocation,
                    supplierInfo
                )

                if (supplierName.isBlank()) {
                    Toast.makeText(context, "Please Enter the Supplier's Name", Toast.LENGTH_LONG)
                        .show()
                } else if (supplierContact.isBlank()) {
                    Toast.makeText(
                        context,
                        "Please Enter the Supplier's Contact",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    updateSupplier(updatedSupplier)
                    Toast.makeText(context, "${supplierName}'s details updated successfully", Toast.LENGTH_LONG)
                        .show()
                    navigateBack()
                }

            },
            buttonName = UPDATE_SUPPLIER
        )

    }
}