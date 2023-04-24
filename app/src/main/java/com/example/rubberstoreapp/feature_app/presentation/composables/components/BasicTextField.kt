package com.example.rubberstoreapp.feature_app.presentation.composables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rubberstoreapp.feature_app.presentation.theme.MainTextFieldTextColor
import com.example.rubberstoreapp.feature_app.presentation.theme.TextFieldBackground

@Composable
fun BasicTextField(
    value: String,
    onValueChange: (value: String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType
) {
    TextField(
        value = value,
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = TextFieldBackground,
            focusedIndicatorColor = Color.DarkGray,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black
        ),
        textStyle = TextStyle(
            color = MainTextFieldTextColor,
            fontSize = 16.sp
        ),
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        placeholder = {
            Placeholder(placeholderText = placeholder)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray, shape = RoundedCornerShape(15.dp))
            .border(
                width = 1.8.dp,
                color = Color.Black,
                shape = RoundedCornerShape(15.dp)
            )
    )
}