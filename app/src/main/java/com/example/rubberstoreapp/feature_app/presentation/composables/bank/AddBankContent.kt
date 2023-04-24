package com.example.rubberstoreapp.feature_app.presentation.composables.bank

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.rubberstoreapp.R
import com.example.rubberstoreapp.core.util.Constants.ADD_BANK
import com.example.rubberstoreapp.core.util.Constants.ADD_CUSTOMER
import com.example.rubberstoreapp.core.util.Constants.BANK_CONTACT
import com.example.rubberstoreapp.core.util.Constants.BANK_LOCATION
import com.example.rubberstoreapp.core.util.Constants.BANK_NAME
import com.example.rubberstoreapp.core.util.Constants.NO_VALUE
import com.example.rubberstoreapp.feature_app.data.local.bank.Bank
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicButton
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicImage
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicScreenColumn
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicTextField

@Composable
fun AddBankContent(
    paddingValues: PaddingValues,
    insertBank: (bank: Bank) -> Unit,
    navigateBack: () -> Unit
) {
    var bankName by remember { mutableStateOf(NO_VALUE) }
    var bankContact by remember { mutableStateOf(NO_VALUE) }
    var bankLocation by remember { mutableStateOf(NO_VALUE) }

    val context = LocalContext.current


    BasicScreenColumn {
        BasicImage(
            drawableResource = R.drawable.bank,
            width = 200.dp,
            height = 200.dp,
            backgroundColor = Color.LightGray
        )

        Spacer(modifier = Modifier.height(50.dp))

        BasicTextField(
            value = bankName,
            onValueChange = { name -> bankName = name },
            placeholder = BANK_NAME,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = bankContact,
            onValueChange = { contact -> bankContact = contact },
            placeholder = BANK_CONTACT,
            keyboardType = KeyboardType.Phone
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = bankLocation,
            onValueChange = { location -> bankLocation = location },
            placeholder = BANK_LOCATION,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(50.dp))

        BasicButton(
            onClick = {
                val bank = Bank(0, bankName, bankContact, bankLocation)
                if (bankName.isNotBlank()) {
                    insertBank(bank)
                    Toast.makeText(context, "$bankName has been successfully added", Toast.LENGTH_LONG).show()
                    navigateBack()
                }
                else Toast.makeText(context, "Please Enter Bank Name", Toast.LENGTH_LONG).show()
            },
            buttonName = ADD_BANK
        )
    }

}