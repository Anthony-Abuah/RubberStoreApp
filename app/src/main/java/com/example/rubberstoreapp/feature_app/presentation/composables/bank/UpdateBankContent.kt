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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.rubberstoreapp.R
import com.example.rubberstoreapp.core.util.Constants.BANK_CONTACT
import com.example.rubberstoreapp.core.util.Constants.BANK_LOCATION
import com.example.rubberstoreapp.core.util.Constants.BANK_NAME
import com.example.rubberstoreapp.core.util.Constants.UPDATE_BANK
import com.example.rubberstoreapp.feature_app.data.local.bank.Bank
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicButton
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicImage
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicScreenColumn
import com.example.rubberstoreapp.feature_app.presentation.composables.components.BasicTextField


@Composable
fun UpdateBankContent(
    padding: PaddingValues,
    bank: Bank,
    updateBank: (bank: Bank) -> Unit,
    updateBankName: (name: String) -> Unit,
    updateBankContact: (contact: String) -> Unit,
    updateBankLocation: (location: String) -> Unit,
    navigateBack: () -> Unit
) {
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
            value = bank.bankName,
            onValueChange = { name ->
                updateBankName(name)
            },
            placeholder = BANK_NAME,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = bank.bankContact,
            onValueChange = { contact ->
                updateBankContact(contact)
            },
            placeholder = BANK_CONTACT,
            keyboardType = KeyboardType.Phone
        )

        Spacer(modifier = Modifier.height(25.dp))

        BasicTextField(
            value = bank.bankLocation,
            onValueChange = { location ->
                updateBankLocation(location)
            },
            placeholder = BANK_LOCATION,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(50.dp))

        BasicButton(
            onClick = {
                val bankId = bank.bankId
                val bankName = bank.bankName
                val bankContact = bank.bankContact
                val bankLocation = bank.bankLocation

                val updatedBank = Bank(bankId, bankName, bankContact, bankLocation)

                if (bankName.isBlank()) {
                    Toast.makeText(context, "Please Enter the Bank's Name", Toast.LENGTH_LONG)
                        .show()
                } else if (bankContact.isBlank()) {
                    Toast.makeText(context, "Please Enter the Bank's Contact", Toast.LENGTH_LONG)
                        .show()
                } else {
                    updateBank(updatedBank)
                    Toast.makeText(context, "$bankName details have been successfully updated", Toast.LENGTH_LONG).show()
                    navigateBack()
                }
            },
            buttonName = UPDATE_BANK
        )
    }
}