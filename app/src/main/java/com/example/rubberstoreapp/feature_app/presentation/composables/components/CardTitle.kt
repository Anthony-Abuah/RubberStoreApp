package com.example.rubberstoreapp.feature_app.presentation.composables.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.example.rubberstoreapp.feature_app.presentation.theme.ButtonBackgroundDisabled
import com.example.rubberstoreapp.feature_app.presentation.theme.CardTitleColor

@Composable
fun CardTitle(
    title: String
) {
    Text(
        text = title,
        fontWeight= FontWeight.Bold,
        color = CardTitleColor,
        fontSize = 16.sp
    )
}

@Composable
fun ReportCardTitle(
    title: String
) {
    Text(
        text = title,
        fontWeight= FontWeight.SemiBold,
        color = Color.White,
        fontSize = 14.sp
    )
}

@Composable
fun ReportCardHeader(
    title: String
) {
    Text(
        text = title,
        fontWeight= FontWeight.Bold,
        color = ButtonBackgroundDisabled,
        fontSize = 17.sp
    )
}

@Composable
fun ReportCardHeader1(
    title: String
) {
    Text(
        text = title,
        fontWeight= FontWeight.ExtraBold,
        color = ButtonBackgroundDisabled,
        fontSize = 20.sp,
        textDecoration = TextDecoration.Underline
    )
}

@Composable
fun ReportCardHeaderMenu(
    title: String
) {
    Text(
        text = title,
        fontWeight= FontWeight.ExtraBold,
        color = Color.White,
        fontSize = 20.sp,
        textDecoration = TextDecoration.None
    )
}

@Composable
fun ReportCardHeaderMenu2(
    title: String
) {
    Text(
        text = title,
        fontWeight= FontWeight.ExtraBold,
        color = Color.Black,
        fontSize = 20.sp,
        textDecoration = TextDecoration.None
    )
}