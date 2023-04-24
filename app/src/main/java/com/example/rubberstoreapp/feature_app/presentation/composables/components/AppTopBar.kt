package com.example.rubberstoreapp.feature_app.presentation.composables.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.rubberstoreapp.feature_app.presentation.theme.AppBarBackground

@Composable
fun AppTopBar(
    label: String,
) {
    TopAppBar (
        title = {
            AppBarTitle(title = label)
        },
        backgroundColor = AppBarBackground,

    )
}

@Composable
fun ScreenTopBar(
    label: String,
    navigateBack: () -> Unit
) {
    TopAppBar (
        title = {
            AppBarTitle(title = label)
        },
        backgroundColor = AppBarBackground,
        navigationIcon = {
            IconButton(
                onClick = navigateBack
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    )
}