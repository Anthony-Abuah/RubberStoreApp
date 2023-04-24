package com.example.rubberstoreapp.feature_app.presentation.composables.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rubberstoreapp.core.util.Constants.NO
import com.example.rubberstoreapp.core.util.Constants.YES
import com.example.rubberstoreapp.feature_app.presentation.theme.DeleteIconColor

@Composable
fun DeleteAlertDialog(
    dialogTitle: String,
    dialogMessage: String,
    openDialog: Boolean,
    delete: () -> Unit,
    closeDialog: () -> Unit,
) {
    if (openDialog){

        AlertDialog(
            onDismissRequest = closeDialog,
            text = {
                Column {
                    Text(
                        color= DeleteIconColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        text = dialogTitle
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        color= Color.Red,
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp,
                        text = "NB: Items deleted cannot be recovered"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = dialogMessage
                    )
                }

            },
            confirmButton = {
                TextButton(
                    onClick = {
                        delete()
                        closeDialog()
                    }
                ) {
                    Text(
                        fontWeight =  FontWeight.Bold,
                        text = YES,
                        color = DeleteIconColor
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        closeDialog()
                    }

                ) {
                    Text(
                        text = NO
                    )
                }
            }
        )
    }
}