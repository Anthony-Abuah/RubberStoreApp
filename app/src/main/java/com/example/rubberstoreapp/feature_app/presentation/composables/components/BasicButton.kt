package com.example.rubberstoreapp.feature_app.presentation.composables.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rubberstoreapp.feature_app.presentation.theme.ButtonBackground
import com.example.rubberstoreapp.feature_app.presentation.theme.ButtonBackgroundDisabled

@Composable
fun BasicButton(
    onClick: () -> Unit,
    buttonName: String
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = ButtonBackground,
            contentColor = Color.White,
            disabledBackgroundColor = ButtonBackgroundDisabled
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Text(
            text = buttonName
        )
    }
}
