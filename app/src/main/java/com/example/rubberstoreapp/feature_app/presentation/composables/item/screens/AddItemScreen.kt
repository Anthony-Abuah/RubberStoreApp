package com.example.rubberstoreapp.feature_app.presentation.composables.item.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.feature_app.presentation.composables.item.AddItemContent
import com.example.rubberstoreapp.feature_app.presentation.composables.item.AddItemTopBar
import com.example.rubberstoreapp.feature_app.presentation.view_model.ItemViewModel

@Composable
fun AddItemScreen(
    itemViewModel: ItemViewModel = hiltViewModel(),
    navigateBackToItemListScreen: () -> Unit
    ){

    Scaffold(
        topBar = {
            AddItemTopBar(
                navigateBack = navigateBackToItemListScreen
            )
        },
        content = { padding ->
            AddItemContent(
                paddingValues = padding,
                insertItem = { item ->
                    itemViewModel.addItem(item)
                },
                navigateBack = navigateBackToItemListScreen
            )
        }
    )
}