package com.example.rubberstoreapp.feature_app.presentation.view_model.states

import com.example.rubberstoreapp.core.util.Constants.NO_VALUE

data class SupplierState (
    val supplierId: Int = 0,
    val supplierName: String = NO_VALUE,
    val supplierContact: String = NO_VALUE,
    val supplierLocation: String = NO_VALUE,
    val supplierInfo: String = NO_VALUE

)