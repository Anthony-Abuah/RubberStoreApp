package com.example.rubberstoreapp.feature_app.presentation.composables.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.example.rubberstoreapp.feature_app.presentation.theme.CardContactColor

@Composable
fun CardContact(
    contact: String
) {
    Text(
        text = contact,
        color = CardContactColor,
        fontSize = 15.sp,
        textDecoration = TextDecoration.Underline
    )
}