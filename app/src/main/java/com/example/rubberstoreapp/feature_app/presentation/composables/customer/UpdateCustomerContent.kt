package com.example.rubberstoreapp.feature_app.presentation.composables.customer

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
import com.example.rubberstoreapp.core.util.Constants.CUSTOMER_CONTACT
import com.example.rubberstoreapp.core.util.Constants.CUSTOMER_INFO
import com.example.rubberstoreapp.core.util.Constants.CUSTOMER_LOCATION
import com.example.rubberstoreapp.core.util.Constants.CUSTOMER_NAME
import com.example.rubberstoreapp.core.util.Constants.UPDATE_CUSTOMER
import com.example.rubberstoreapp.feature_app.data.local.customer.Customer
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicButton
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicImage
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicScreenColumn
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicTextField


@Composable
fun UpdateCustomerContent(
    padding: PaddingValues,
    customer: Customer,
    updateCustomer: (id: Int, name: String, contact: String, location: String, info: String) -> Unit,
    updateCustomerName: (name: String) -> Unit,
    updateCustomerContact: (contact: String) -> Unit,
    updateCustomerLocation: (location: String) -> Unit,
    updateCustomerInfo: (info: String) -> Unit,
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    BasicScreenColumn {
        BasicImage(drawableResource = R.drawable.customer,
            width = 200.dp,
            height = 200.dp,
            backgroundColor = Color.Transparent)

        Spacer(modifier = Modifier.height(50.dp))

        BasicTextField(value = customer.customerName,
            onValueChange = {name ->
                updateCustomerName(name)
            },
            placeholder = CUSTOMER_NAME,
            keyboardType = KeyboardType.Text)

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(value = customer.customerContact,
            onValueChange = {contact ->
                updateCustomerContact(contact)
            },
            placeholder = CUSTOMER_CONTACT,
            keyboardType = KeyboardType.Phone)

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(value = customer.customerLocation,
            onValueChange = {location ->
                updateCustomerLocation(location)
            },
            placeholder = CUSTOMER_LOCATION,
            keyboardType = KeyboardType.Text)

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(value = customer.customerInfo,
            onValueChange = {info ->
                updateCustomerInfo(info)
            },
            placeholder = CUSTOMER_INFO,
            keyboardType = KeyboardType.Text)

        Spacer(modifier = Modifier.height(50.dp))

        BasicButton(onClick = {
            val customerName = customer.customerName
            val customerContact = customer.customerContact
            val customerLocation = customer.customerLocation
            val customerInfo = customer.customerInfo

            if (customerName.isBlank()){
                Toast.makeText(context,"Please Enter the Customer's Name", Toast.LENGTH_LONG).show()
            }
            else if (customerContact.isBlank()){
                Toast.makeText(context,"Please Enter the Customer's Contact", Toast.LENGTH_LONG).show()
            }
            else{
                updateCustomer(customer.customerId, customerName, customerContact, customerLocation, customerInfo)
                Toast.makeText(context, "Customer updated successfully",Toast.LENGTH_LONG).show()
                navigateBack()
            }

        }, buttonName = UPDATE_CUSTOMER
        )
    }
}