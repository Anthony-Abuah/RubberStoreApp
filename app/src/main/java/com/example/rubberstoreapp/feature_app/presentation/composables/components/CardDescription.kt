package com.example.rubberstoreapp.feature_app.presentation.composables.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.example.rubberstoreapp.feature_app.presentation.theme.ButtonBackgroundDisabled
import com.example.rubberstoreapp.feature_app.presentation.theme.CardDescriptionColor

@Composable
fun CardDescription(
    info: String
) {
    Text(
        text = info,
        color = CardDescriptionColor,
        fontSize = 14.sp,
        textDecoration = TextDecoration.None
    )
}

@Composable
fun ReportCardValueItems(
    info: String
) {
    Text(
        text = info,
        color = ButtonBackgroundDisabled,
        fontSize = 15.sp,
        textDecoration = TextDecoration.None
    )
}

@Composable
fun ReportCardValue(
    value: String
) {
    Text(
        text = value,
        fontWeight= FontWeight.Bold,
        color = Color.White,
        fontSize = 18.sp,
        textDecoration = TextDecoration.None
    )
}