package com.example.rubberstoreapp.feature_app.presentation.composables.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.unit.dp
import com.example.rubberstoreapp.feature_app.presentation.theme.ButtonBackground


@Composable
fun AddFloatingActionButton(
    navigateToAnotherScreen: () -> Unit,
    description: String
) {
    FloatingActionButton(
        modifier = Modifier
            .padding(20.dp),
        onClick = navigateToAnotherScreen,
        backgroundColor = ButtonBackground,
        contentColor = Color.White
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = description
        )
    }
}