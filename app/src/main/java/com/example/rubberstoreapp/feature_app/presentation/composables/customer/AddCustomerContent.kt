package com.example.rubberstoreapp.feature_app.presentation.composables.customer

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
import com.example.rubberstoreapp.R
import com.example.rubberstoreapp.core.util.Constants.ADD_CUSTOMER
import com.example.rubberstoreapp.core.util.Constants.CUSTOMER_CONTACT
import com.example.rubberstoreapp.core.util.Constants.CUSTOMER_INFO
import com.example.rubberstoreapp.core.util.Constants.CUSTOMER_LOCATION
import com.example.rubberstoreapp.core.util.Constants.CUSTOMER_NAME
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.customer.Customer
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicButton
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicImage
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicScreenColumn
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicTextField

@Composable
fun AddCustomerContent(
    padding: PaddingValues,
    insertCustomer: (customer: Customer) -> Unit,
    navigateBack: () -> Unit
) {
    var customerName by remember { mutableStateOf(NO_VALUE) }
    var customerContact by remember { mutableStateOf(NO_VALUE) }
    var customerLocation by remember { mutableStateOf(NO_VALUE) }
    var customerInfo by remember { mutableStateOf(NO_VALUE) }

    val context = LocalContext.current

    BasicScreenColumn {

        BasicImage(
            drawableResource = R.drawable.customer,
            width = 200.dp,
            height = 200.dp,
            backgroundColor = Color.Transparent
        )

        Spacer(modifier = Modifier.height(50.dp))

        BasicTextField(
            value = customerName,
            onValueChange = { name -> customerName = name },
            placeholder = CUSTOMER_NAME,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = customerContact,
            onValueChange = { contact -> customerContact = contact },
            placeholder = CUSTOMER_CONTACT,
            keyboardType = KeyboardType.Phone
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = customerLocation,
            onValueChange = { location -> customerLocation = location },
            placeholder = CUSTOMER_LOCATION,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = customerInfo,
            onValueChange = { info -> customerInfo = info },
            placeholder = CUSTOMER_INFO,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(50.dp))

        BasicButton(
            onClick = {
                val customer =
                    Customer(0, customerName, customerContact, customerLocation, customerInfo)
                if (customerName.isBlank()) {
                    Toast.makeText(context, "Please Enter the Customer's Name", Toast.LENGTH_LONG)
                        .show()
                } else if (customerContact.isBlank()) {
                    Toast.makeText(
                        context,
                        "Please Enter the Customer's Contact",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    insertCustomer(customer)
                    Toast.makeText(context, "Customer added successfully", Toast.LENGTH_LONG).show()
                    navigateBack()
                }
            },
            buttonName = ADD_CUSTOMER
        )
    }
}