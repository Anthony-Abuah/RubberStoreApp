package com.example.rubberstoreapp.feature_app.presentation.view_model.states

import com.example.rubberstoreapp.core.util.Constants.NO_VALUE

data class BankState (
    val bankId: Int = 0,
    val bankName: String = NO_VALUE,
    val bankContact: String = NO_VALUE,
    val bankLocation: String = NO_VALUE,

)