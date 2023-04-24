package com.example.rubberstoreapp.feature_app.presentation.composables.item

import androidx.compose.runtime.Composable
import com.example.rubberstoreapp.core.util.Constants.ADD_ITEM
import com.example.rubberstoreapp.feature_app.presentation.composables.components.ScreenTopBar

@Composable
fun AddItemTopBar(
    navigateBack: () -> Unit
) {
    ScreenTopBar(label = ADD_ITEM) {
        navigateBack()
    }
}