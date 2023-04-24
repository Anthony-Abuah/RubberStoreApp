package com.example.rubberstoreapp.feature_app.presentation.composables.supplier

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import com.example.rubberstoreapp.core.util.Constants.UPDATE_SUPPLIER
import com.example.rubberstoreapp.feature_app.presentation.composables.components.ScreenTopBar

@Composable
fun UpdateSupplierTopBar(
    navigateBack: () -> Unit
) {
    ScreenTopBar(label = UPDATE_SUPPLIER) {
        navigateBack()
    }
}