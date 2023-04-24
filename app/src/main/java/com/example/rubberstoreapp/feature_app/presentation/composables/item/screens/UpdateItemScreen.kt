package com.example.rubberstoreapp.feature_app.presentation.composables.item.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rubberstoreapp.feature_app.presentation.composables.item.UpdateItemContent
import com.example.rubberstoreapp.feature_app.presentation.composables.item.UpdateItemTopBar
import com.example.rubberstoreapp.feature_app.presentation.view_model.ItemViewModel


@Composable
fun UpdateItemScreen (
    itemViewModel: ItemViewModel = hiltViewModel(),
    itemId: Int,
    navigateBackToItemListScreen: () -> Unit
){

    LaunchedEffect(Unit) {
        itemViewModel.getItem(itemId)
    }

    Scaffold(
        topBar = {
            UpdateItemTopBar(
                navigateBack = navigateBackToItemListScreen
            )
        },
        content = { padding ->
            UpdateItemContent(
                padding = padding,
                item = itemViewModel.item,
                updateItem = { item->
                    itemViewModel.updateItem(item)
                },
                updateItemName = {name->
                    itemViewModel.updateItemName(name) },
                updateItemManufacturer = {manufacturer->
                    itemViewModel.updateItemManufacturer(manufacturer) },
                updateItemDescription = {description->
                    itemViewModel.updateItemDescription(description) },
                navigateBack = navigateBackToItemListScreen
            )
        }
    )
}