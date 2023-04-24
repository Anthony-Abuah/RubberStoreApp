package com.example.rubberstoreapp.feature_app.presentation.composables.item

import androidx.compose.runtime.Composable
import com.example.rubberstoreapp.core.util.Constants.UPDATE_ITEM
import com.example.rubberstoreapp.feature_app.presentation.composables.components.ScreenTopBar

@Composable
fun UpdateItemTopBar(
    navigateBack: () -> Unit
) {
    ScreenTopBar(label = UPDATE_ITEM) {
        navigateBack()
    }
}