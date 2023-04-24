package com.example.rubberstoreapp.feature_app.presentation.composables.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.example.rubberstoreapp.feature_app.presentation.theme.CardDescriptionColor
import com.example.rubberstoreapp.feature_app.presentation.theme.PlaceHolderTextFieldTextColor

@Composable
fun Placeholder(
    placeholderText: String
) {
    Text(
        text = placeholderText,
        color = PlaceHolderTextFieldTextColor,
        fontSize = 16.sp,
        textDecoration = TextDecoration.None
    )
}