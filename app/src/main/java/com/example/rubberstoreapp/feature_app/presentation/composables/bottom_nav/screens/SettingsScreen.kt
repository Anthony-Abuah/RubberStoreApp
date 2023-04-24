package com.example.rubberstoreapp.feature_app.presentation.composables.bottom_nav.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@Composable
fun SettingsScreen() {
    Text(
        text = "View Settings Here",
        fontSize = 20.sp,
        textAlign = TextAlign.Center
    )
}