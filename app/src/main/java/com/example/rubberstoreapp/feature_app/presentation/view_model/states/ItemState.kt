package com.example.rubberstoreapp.feature_app.presentation.view_model.states

import com.example.rubberstoreapp.core.util.Constants.NO_VALUE

data class ItemState (
    val itemId: Int = 0,
    val itemName: String = NO_VALUE,
    val itemManufacturer: String = NO_VALUE,
    val itemDescription: String = NO_VALUE

)