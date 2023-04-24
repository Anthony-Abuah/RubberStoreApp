package com.example.rubberstoreapp.feature_app.presentation.composables.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun AppBarTitle(
    title: String
) {
    Text(
        text = title,
        fontWeight= FontWeight.Bold,
        color = Color.White,
        fontSize = 20.sp
    )
}