package com.example.rubberstoreapp.feature_app.presentation.composables.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.rubberstoreapp.core.util.Constants.DELETE_RECORD
import com.example.rubberstoreapp.feature_app.presentation.theme.DeleteIconColor

@Composable
fun DeleteIcon(
    delete: () -> Unit
) {
    IconButton(
        modifier = Modifier.fillMaxSize(),
        onClick = {
            delete()
        }
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            tint= DeleteIconColor,
            contentDescription = DELETE_RECORD,
        )
    }
}
