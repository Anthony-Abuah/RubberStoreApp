package com.example.rubberstoreapp.feature_app.presentation.composables.debt

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import com.example.rubberstoreapp.core.util.Constants.ADD_DEBT
import com.example.rubberstoreapp.feature_app.presentation.composables.components.ScreenTopBar

@Composable
fun AddDebtTopBar(
    navigateBack: () -> Unit
) {
    ScreenTopBar(label = ADD_DEBT) {
        navigateBack()
    }
}